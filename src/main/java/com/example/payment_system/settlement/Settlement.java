package com.example.payment_system.settlement;

import com.example.payment_system.payment.Payment;
import jakarta.persistence.*;

@Entity
public class Settlement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Payment payment;
    
    private Long restaurantAmount;
    private Long deliveryAmount;
    private Long platformAmount;
    
    public Settlement() {
    }
    
    public Long getId() {
        return id;
    }
    public Long getRestaurantAmount() {
        return restaurantAmount;
    }
    public void setRestaurantAmount(Long restaurantAmount) {
        this.restaurantAmount = restaurantAmount;
    }
    public Long getDeliveryAmount() {
        return deliveryAmount;
    }
    public void setDeliveryAmount(Long deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }
    public Long getPlatformAmount() {
        return platformAmount;
    }
    public void setPlatformAmount(Long platformAmount) {
        this.platformAmount = platformAmount;
    }
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
