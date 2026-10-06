package com.example.payment_system.payment;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class WebhookSignature {
    
    public static String generate(String eventId,
                                  boolean success,
                                  String secret){
        try{
            String payload = eventId + ":" + success;
            
            Mac mac = Mac.getInstance("HmacSHA256");
            
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                    "HmacSHA256");
            
            mac.init(key);
            
            byte[] hmac =  mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            
            return HexFormat.of().formatHex(hmac);
        } catch (Exception e){
            throw new IllegalStateException("Could not generate webhook signature",e);
        }
    }
}
