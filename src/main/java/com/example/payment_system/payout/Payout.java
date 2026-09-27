package com.example.payment_system.payout;

import com.example.payment_system.account.Account;
import com.example.payment_system.payment.Payment;
import com.example.payment_system.settlement.Settlement;
import jakarta.persistence.*;

@Entity
public class Payout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Account account;
    
    @ManyToOne
    private Settlement settlement;
    
    private Long amount;
    
    public Payout(){}
    
    public Long  getId() {
        return id;
    }
    
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public Settlement getSettlement() {
        return settlement;
    }
    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
