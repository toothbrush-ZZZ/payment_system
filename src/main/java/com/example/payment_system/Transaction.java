package com.example.payment_system;

import jakarta.persistence.*;

@Entity
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long amount;
    
    @ManyToOne
    private Account sender;
    
    @ManyToOne
    private Account receiver;
    
    public Transaction() {}
    
    public Transaction(Long amount,  Account sender, Account receiver) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
    }
    public Long getId() {
        return id;
    }
    
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
    public Account getSender() {
        return sender;
    }
    public void setSender(Account sender) {
        this.sender = sender;
    }
    public Account getReceiver() {
        return receiver;
    }
    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }
}
