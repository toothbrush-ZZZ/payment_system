package com.example.payment_system.payment;

public interface PaymentProvider {
    
    boolean processPayment(Payment payment);
}
