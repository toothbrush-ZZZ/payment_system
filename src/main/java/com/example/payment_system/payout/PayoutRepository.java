package com.example.payment_system.payout;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<Payout, Long> {
}