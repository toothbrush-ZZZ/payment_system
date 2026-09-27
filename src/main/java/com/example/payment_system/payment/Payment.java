package com.example.payment_system.payment;

import com.example.payment_system.account.Account;
import jakarta.persistence.*;

@Entity
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Account customer;
    
    private Long amount;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    
    public Payment() {
    }
    
    public Long getId() {
        return id;
    }
    public Account getCustomer() {
        return customer;
    }
    public void setCustomer(Account customer) {
        this.customer = customer;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
