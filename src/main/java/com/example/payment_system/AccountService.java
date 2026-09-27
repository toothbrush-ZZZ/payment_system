package com.example.payment_system;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;
    
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
    
    public Account createAccount(Account account) {
        
        if (account.getName() == null || account.getName().isBlank()) {
            throw new IllegalArgumentException("Account name is required");
        }
        
        if (account.getType() == null || account.getType().isBlank()) {
            throw new IllegalArgumentException("Account type is required");
        }
        
        return accountRepository.save(account);
    }
}