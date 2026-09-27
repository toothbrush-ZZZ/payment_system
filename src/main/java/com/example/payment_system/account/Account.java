package com.example.payment_system.account;

import jakarta.persistence.*;

@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    private AccountType type;
    
    private Long  balance;
    
    public Account() {}
    
    public Account(String name, AccountType type) {
        this.name = name;
        this.type = type;
        this.balance = 0L;
    }
    
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public AccountType getType() {
        return type;
    }
    public void setType(AccountType type) {
        this.type = type;
    }
    public Long getBalance() {
        return balance;
    }
    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
