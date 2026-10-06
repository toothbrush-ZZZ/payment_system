package com.example.payment_system.payment;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks")
public class PaymentWebhookController {

    private final PaymentService paymentService;
    
    public PaymentWebhookController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @PostMapping("/payment/{paymentId}")
    public Payment handlePaymentWebhook(@PathVariable("paymentId") Long paymentId,
                                        @RequestParam boolean success){
        
        return paymentService.handlePaymentWebhook(paymentId, success);
    }
}
