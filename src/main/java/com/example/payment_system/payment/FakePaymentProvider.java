package com.example.payment_system.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class FakePaymentProvider implements PaymentProvider{
    
    private final RestClient restClient;
    
    @Value("${payment.webhook.secret}")
    private String secret;
    
    public FakePaymentProvider() {
        this.restClient = RestClient.builder().baseUrl("http://localhost:8080").build();
    }
    @Override
    public boolean processPayment(Payment payment) {
        return payment.getAmount() % 2 == 0;
    }
    
    @Override
    public String generateWebhookSignature(
            String eventId,
            boolean success) {
        
        return WebhookSignature.generate(
                eventId,
                success,
                secret
        );
    }
    
    @Override
    public void sendWebhook(
            Long paymentId,
            boolean success,
            String eventId) {
        
        String signature = generateWebhookSignature(
                eventId,
                success
        );
        
        restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/webhooks/payment/{paymentId}")
                        .queryParam("success", success)
                        .queryParam("eventId", eventId)
                        .queryParam("signature", signature)
                        .build(paymentId))
                .retrieve()
                .body(String.class);
    }
}
