package com.example.payment_system.payment;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fake-provider")
public class FakePaymentProviderController {
    
    private final PaymentProvider paymentProvider;
    
    public FakePaymentProviderController(PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }
    
    @PostMapping("/webhook/{paymentId}")
    public void sendWebhook(
            @PathVariable Long paymentId,
            @RequestParam boolean success,
            @RequestParam String eventId) {
        
        paymentProvider.sendWebhook(
                paymentId,
                success,
                eventId
        );
    }
}