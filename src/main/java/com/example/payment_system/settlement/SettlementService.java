package com.example.payment_system.settlement;

import com.example.payment_system.payment.Payment;
import com.example.payment_system.payment.PaymentRepository;
import com.example.payment_system.payment.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettlementService {
    
    private final SettlementRepository settlementRepository;
    private final PaymentRepository paymentRepository;
    
    public SettlementService(SettlementRepository settlementRepository, PaymentRepository paymentRepository) {
        this.settlementRepository = settlementRepository;
        this.paymentRepository = paymentRepository;
    }
    
    public List<Settlement> getSettlements() {
        return settlementRepository.findAll();
    }
    
    public Settlement createSettlement(Long paymentId){
        
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        
        if(payment.getStatus() != PaymentStatus.COMPLETED){
            throw new IllegalStateException("Payment is not COMPLETED");
        }
        
        // Prevent dubplicate payment for one settlement
        if (settlementRepository.findByPaymentId(paymentId).isPresent()) {
            throw new IllegalArgumentException(
                    "Payment has already been settled");
        }
        
        Long amount = payment.getAmount();
        
        Settlement settlement = new Settlement();
        
        settlement.setPayment(payment);
        settlement.setRestaurantAmount(amount * 80 / 100);
        settlement.setDeliveryAmount(amount * 10 / 100);
        settlement.setPlatformAmount(
                amount - settlement.getRestaurantAmount() - settlement.getDeliveryAmount()
        );
        
        return settlementRepository.save(settlement);
    }
}
