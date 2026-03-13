package com.tltn.identity.infrastructure.security;

import com.tltn.identity.application.port.TokenGenerator;
import com.tltn.identity.domain.entities.Account;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class JwtTokenGenerator implements TokenGenerator {

    @Override
    public String generateAccessToken(Account account) {
        // Mock implementation for JWT generation
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." + account.getId() + ".access";
    }

    @Override
    public String generateRefreshToken(Account account) {
        // Simple random UUID for refresh token can also work, or a JWT
        return UUID.randomUUID().toString();
    }
}
