package com.example.payment_system.payment;

import com.example.payment_system.transaction.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final PaymentProvider paymentProvider;
    private final TransactionService transactionService;
    private final PaymentWebhookEventRepository webhookEventRepository;
    
    public PaymentService(PaymentRepository paymentRepository,
                          PaymentProvider paymentProvider,
                          TransactionService transactionService,
                          PaymentWebhookEventRepository webhookEventRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentProvider = paymentProvider;
        this.transactionService = transactionService;
        this.webhookEventRepository = webhookEventRepository;
    }
    
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
    
    public Payment createPayment(Payment payment) {
        
        if (payment.getAmount() == null || payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        payment.setStatus(PaymentStatus.PENDING);
        
        return paymentRepository.save(payment);
    }
    
    @Transactional
    public Payment processPayment(Long paymentId) {
        
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment with id: " + paymentId + " not found"));
        
        // Prevent duplicate processing
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new IllegalArgumentException(
                    "Payment has already been processed");
        }
        
        payment.setStatus(PaymentStatus.PROCESSING);
        
        boolean successful = paymentProvider.processPayment(payment);
        
        if (!successful) {
            payment.setStatus(PaymentStatus.FAILED);
        }
        
        return paymentRepository.save(payment);
    }
    
    @Transactional
    public Payment handlePaymentWebhook(
            Long paymentId,
            boolean success,
            String eventId) {
        
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Payment with id: "
                                        + paymentId
                                        + " not found"));
        
        if (webhookEventRepository.findByEventId(eventId).isPresent()) {
            throw new IllegalArgumentException(
                    "Webhook event has already been processed");
        }
        
        if (payment.getStatus() != PaymentStatus.PROCESSING) {
            throw new IllegalArgumentException(
                    "Payment is not waiting for a webhook");
        }
        
        PaymentWebhookEvent event = new PaymentWebhookEvent();
        event.setEventId(eventId);
        event.setPayment(payment);
        event.setSuccess(success);
        
        webhookEventRepository.save(event);
        
        if (success) {
            
            transactionService.receivePayment(
                    payment.getCustomer().getId(),
                    payment.getAmount()
            );
            
            payment.setStatus(PaymentStatus.COMPLETED);
            
        } else {
            
            payment.setStatus(PaymentStatus.FAILED);
        }
        
        return paymentRepository.save(payment);
    }
}
