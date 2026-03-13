package com.tltn.identity.domain.entities;

import com.tltn.identity.domain.seedworks.Entity;
import com.tltn.identity.domain.seedworks.IAggregateRoot;
import com.tltn.identity.domain.valueobjects.TokenString;

import java.time.Instant;
import java.util.UUID;

public class RefreshToken extends Entity<UUID> implements IAggregateRoot {

    private final TokenString token;
    private final UUID accountId; // Link by ID since cross-aggregate
    private final Instant expiryDate;
    private boolean revoked;

    // Default constructor for ORM frameworks
    protected RefreshToken() {
        super(null);
        this.token = null;
        this.accountId = null;
        this.expiryDate = null;
    }

    private RefreshToken(UUID id, TokenString token, UUID accountId, Instant expiryDate, boolean revoked) {
        super(id);
        this.token = token;
        this.accountId = accountId;
        this.expiryDate = expiryDate;
        this.revoked = revoked;
    }

    public static RefreshToken create(TokenString token, UUID accountId, Instant expiryDate) {
        return new RefreshToken(UUID.randomUUID(), token, accountId, expiryDate, false);
    }

    public static RefreshToken createWithId(UUID id, TokenString token, UUID accountId, Instant expiryDate, boolean revoked) {
        return new RefreshToken(id, token, accountId, expiryDate, revoked);
    }

    public TokenString getToken() {
        return token;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiryDate);
    }

    public void revoke() {
        this.revoked = true;
    }
}
