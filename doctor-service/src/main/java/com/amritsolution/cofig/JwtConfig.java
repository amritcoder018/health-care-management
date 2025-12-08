package com.amritsolution.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {

    private final String secret = "your-256-bit-secret-key-must-be-long-1234567890";

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(
                new SecretKeySpec(secret.getBytes(), "HmacSHA256")
        ).build();
    }
}