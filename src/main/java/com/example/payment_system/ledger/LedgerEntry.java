package com.example.payment_system.ledger;

import com.example.payment_system.account.Account;
import com.example.payment_system.transaction.Transaction;
import jakarta.persistence.*;

@Entity
public class LedgerEntry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Transaction transaction;
    
    @ManyToOne
    private Account account;
    
    @Enumerated(EnumType.STRING)
    private LedgerEntryType type;
    
    private Long amount;
    
    public LedgerEntry() {}
    
    public Long getId() {
        return id;
    }
    
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    public Transaction getTransaction() {
        return transaction;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    public Account getAccount() {
        return account;
    }
    
    public void setType(LedgerEntryType type) {
        this.type = type;
    }
    public LedgerEntryType getType() {
        return type;
    }
    
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public Long getAmount() {
        return amount;
    }
}
