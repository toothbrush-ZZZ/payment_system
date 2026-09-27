package com.example.payment_system.transaction;

import com.example.payment_system.account.Account;
import com.example.payment_system.account.AccountRepository;
import com.example.payment_system.account.AccountType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }
    
    @Transactional
    public Transaction transfer(Long senderId, Long receiverId, Long amount) {
        
        // Validate amount
        if(amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        
        // Sender and Receiver validation
        if (senderId == null || receiverId == null) {
            throw new IllegalArgumentException("Sender and receiver IDs are required");
        }
        if(senderId.equals(receiverId)) {
            throw new IllegalArgumentException("Sender id cannot be equal to receiver id");
        }
        
        // Find Sender and Receiver
        Account sender = accountRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Sender account not found."));
        Account receiver = accountRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver account not found."));
        
        // Check sender balance and Update balance
        if(sender.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds.");
        }
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        
        // Save new balance // These are redundant. JPA automatically saves the changes
        accountRepository.save(sender);
        accountRepository.save(receiver);
        
        Transaction transaction = new Transaction(amount, sender, receiver);
        
        return transactionRepository.save(transaction);
    }
    
    public Transaction payout(Long receiverId, Long amount) {
        
        Account platformAccount = accountRepository.findByType(AccountType.PLATFORM)
                .orElseThrow(() -> new IllegalArgumentException("Platform account not found."));
        
        return transfer(platformAccount.getId(), receiverId, amount);
    }
}
