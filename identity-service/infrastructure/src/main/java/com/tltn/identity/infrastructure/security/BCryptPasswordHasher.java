package com.tltn.identity.infrastructure.security;

import com.tltn.identity.application.port.PasswordHasher;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordHasher implements PasswordHasher {

    @Override
    public String hash(String rawPassword) {
        // Mock implementation for BCrypt hashing
        return "bcrypt_hash(" + rawPassword + ")";
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        // Mock implementation for BCrypt matches
        return hashedPassword.equals("bcrypt_hash(" + rawPassword + ")");
    }
}
