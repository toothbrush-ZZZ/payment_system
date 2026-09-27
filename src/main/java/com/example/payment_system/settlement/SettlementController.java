package com.example.payment_system.settlement;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlements")
public class SettlementController {
    
    private final SettlementService settlementService;
    
    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }
    
    @GetMapping
    public List<Settlement> getSettlements() {
        return settlementService.getSettlements();
    }
    
    @PostMapping("/{paymentId}")
    public Settlement createSettlement(@PathVariable Long paymentId) {
        return settlementService.createSettlement(paymentId);
    }
}

