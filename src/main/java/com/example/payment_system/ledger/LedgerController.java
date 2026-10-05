package com.example.payment_system.ledger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ledger")
public class LedgerController {
    
    private final LedgerService ledgerService;
    
    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }
    
    @GetMapping
    public List<LedgerEntry> getLedgerEntries() {
        return ledgerService.getLedgerEntries();
    }
}
