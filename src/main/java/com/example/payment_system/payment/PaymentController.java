package com.example.payment_system.payment;

import com.example.payment_system.account.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @GetMapping
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }
    
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }
    
    @PostMapping("/{id}/process")
    public Payment processPayment(@PathVariable Long id) {
        return paymentService.processPayment(id);
    }
}
