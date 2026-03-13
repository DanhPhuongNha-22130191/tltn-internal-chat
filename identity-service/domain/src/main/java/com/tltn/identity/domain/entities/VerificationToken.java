package com.tltn.identity.domain.entities;

import com.tltn.identity.domain.seedworks.Entity;
import com.tltn.identity.domain.seedworks.IAggregateRoot;
import com.tltn.identity.domain.valueobjects.TokenString;
import com.tltn.identity.domain.valueobjects.TokenType;

import java.time.Instant;
import java.util.UUID;

public class VerificationToken extends Entity<UUID> implements IAggregateRoot {

    private final TokenString token;
    private final UUID accountId; // Reference to Account aggregate
    private final TokenType type;
    private final Instant expiryDate;
    private boolean used;

    // Default constructor for ORM frameworks
    protected VerificationToken() {
        super(null);
        this.token = null;
        this.accountId = null;
        this.type = null;
        this.expiryDate = null;
    }

    private VerificationToken(UUID id, TokenString token, UUID accountId, TokenType type, Instant expiryDate, boolean used) {
        super(id);
        this.token = token;
        this.accountId = accountId;
        this.type = type;
        this.expiryDate = expiryDate;
        this.used = used;
    }

    public static VerificationToken create(TokenString token, UUID accountId, TokenType type, Instant expiryDate) {
        return new VerificationToken(UUID.randomUUID(), token, accountId, type, expiryDate, false);
    }

    public static VerificationToken createWithId(UUID id, TokenString token, UUID accountId, TokenType type, Instant expiryDate, boolean used) {
        return new VerificationToken(id, token, accountId, type, expiryDate, used);
    }

    public TokenString getToken() {
        return token;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public TokenType getType() {
        return type;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public boolean isUsed() {
        return used;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiryDate);
    }

    public void markAsUsed() {
        if (isExpired()) {
            throw new IllegalStateException("Token is already expired");
        }
        if (this.used) {
            throw new IllegalStateException("Token is already used");
        }
        this.used = true;
    }
}
