package com.example.payment_system.payout;

import com.example.payment_system.account.Account;
import com.example.payment_system.account.AccountRepository;
import com.example.payment_system.settlement.Settlement;
import com.example.payment_system.settlement.SettlementRepository;
import com.example.payment_system.transaction.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayoutService {
    
    private final PayoutRepository payoutRepository;
    private final SettlementRepository settlementRepository;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    
    public PayoutService(PayoutRepository payoutRepository,
                         SettlementRepository settlementRepository,
                         AccountRepository accountRepository,
                         TransactionService transactionService) {
        this.payoutRepository = payoutRepository;
        this.settlementRepository = settlementRepository;
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }
    
    public List<Payout> getPayouts(){
        return payoutRepository.findAll();
    }
    
    @Transactional
    public Payout createPayout(Long settlementId, Long accountId, Long amount){
        
        Settlement settlement = settlementRepository.findById(settlementId)
                .orElseThrow(() -> new IllegalArgumentException("settlementId not found"));
        
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("account not found"));
        
        Payout payout = new Payout();
        payout.setSettlement(settlement);
        payout.setAccount(account);
        payout.setAmount(amount);
        
        Payout savedPayout = payoutRepository.save(payout);
        
        transactionService.payout(accountId, amount);
        
        return savedPayout;
    }
}
