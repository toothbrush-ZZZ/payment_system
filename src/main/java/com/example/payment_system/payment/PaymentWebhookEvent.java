package com.example.payment_system.payment;

import jakarta.persistence.*;

@Entity
public class PaymentWebhookEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String eventId;
    
    @ManyToOne
    private Payment payment;
    
    private boolean success;
    
    public PaymentWebhookEvent() {}
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public void getEventId(String eventId) {
        this.eventId = eventId;
    }
    
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
