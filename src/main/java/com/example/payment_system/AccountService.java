package com.example.payment_system;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    
    private List<Account> accounts = new ArrayList<>();
    
    public List<Account> getAccounts() {
        return accounts;
    }
    
    public Account createAccount(Account account) {
        accounts.add(account);
        return account;
    }
}
