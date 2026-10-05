package com.example.payment_system.transaction;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    private final TransactionService transactionService;
    
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }
    
    @PostMapping
    public Transaction transfer(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam Long amount) {
        
        return this.transactionService.transfer(senderId, receiverId, amount);
    }
}
