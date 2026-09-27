package com.example.payment_system.payout;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payouts")
public class PayoutController {
    
    private final PayoutService payoutService;
    
    public PayoutController(PayoutService payoutService) {
        this.payoutService = payoutService;
    }
    
    @GetMapping
    public List<Payout> getPayouts() {
        return payoutService.getPayouts();
    }
    
    @PostMapping
    public Payout createPayout(@RequestParam Long settlementId,
                               @RequestParam Long accountId,
                               @RequestParam Long amount) {
        return payoutService.createPayout(settlementId, accountId, amount);
    }
}
