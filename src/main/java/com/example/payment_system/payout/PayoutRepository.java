package com.example.payment_system.payout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PayoutRepository extends JpaRepository<Payout, Long> {
    
    @Query("""
    SELECT COALESCE(SUM(p.amount), 0)
    FROM Payout p
    WHERE p.settlement.id = :settlementId
      AND p.account.id = :accountId
""")
    Long getTotalPaid(
            @Param("settlementId") Long settlementId,
            @Param("accountId") Long accountId);
    
}