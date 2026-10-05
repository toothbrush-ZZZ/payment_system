package com.example.payment_system.ledger;

import com.example.payment_system.account.Account;
import com.example.payment_system.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LedgerService {
    
    private final LedgerRepository ledgerRepository;
    
    public LedgerService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }
    
    public List<LedgerEntry> getLedgerEntries() {
        return ledgerRepository.findAll();
    }
    
    public void createEntries(Transaction transaction,
                              Account sender,
                              Account receiver,
                              Long amount) {
        
        LedgerEntry debit = new  LedgerEntry();
        
        debit.setTransaction(transaction);
        debit.setAccount(sender);
        debit.setAmount(amount);
        debit.setType(LedgerEntryType.DEBIT);
        
        LedgerEntry credit = new  LedgerEntry();
        
        credit.setTransaction(transaction);
        credit.setAccount(receiver);
        credit.setAmount(amount);
        credit.setType(LedgerEntryType.CREDIT);
        
        ledgerRepository.save(debit);
        ledgerRepository.save(credit);
    }
}
