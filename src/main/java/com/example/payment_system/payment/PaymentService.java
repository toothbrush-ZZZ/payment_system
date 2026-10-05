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
    
    public PaymentService(PaymentRepository paymentRepository,
                          PaymentProvider paymentProvider,
                          TransactionService transactionService) {
        this.paymentRepository = paymentRepository;
        this.paymentProvider = paymentProvider;
        this.transactionService = transactionService;
    }
    
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
    
    public Payment createPayment(Payment payment) {
        
        payment.setStatus(PaymentStatus.PENDING);
        
        return paymentRepository.save(payment);
    }
    
    @Transactional
    public Payment processPayment(Long paymentId) {
        
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment with id: " + paymentId + " not found"));
        
        payment.setStatus(PaymentStatus.PROCESSING);
        
        boolean successful = paymentProvider.processPayment(payment);
        
        if (successful) {
            
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
