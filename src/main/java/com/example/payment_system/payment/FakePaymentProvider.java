package com.example.payment_system.payment;

import org.springframework.stereotype.Component;

@Component
public class FakePaymentProvider implements PaymentProvider{
    
    @Override
    public boolean processPayment(Payment payment) {
        return payment.getAmount() % 2 == 0;
    }
}
