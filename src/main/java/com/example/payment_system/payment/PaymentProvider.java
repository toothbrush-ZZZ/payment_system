package com.example.payment_system.payment;

public interface PaymentProvider {
    
    boolean processPayment(Payment payment);
    
    String generateWebhookSignature(
            String eventId,
            boolean success
    );
    
    void sendWebhook(
            Long paymentId,
            boolean success,
            String eventId
    );
}
