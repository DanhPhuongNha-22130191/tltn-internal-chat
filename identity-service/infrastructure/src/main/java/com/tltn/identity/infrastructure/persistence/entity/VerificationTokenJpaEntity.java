package com.tltn.identity.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "verification_tokens")
public class VerificationTokenJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private UUID accountId;

    @Column(nullable = false)
    private String tokenType;

    @Column(nullable = false)
    private Instant expiryDate;

    @Column(nullable = false)
    private boolean used;

    protected VerificationTokenJpaEntity() {}

    public VerificationTokenJpaEntity(UUID id, String token, UUID accountId, String tokenType, Instant expiryDate, boolean used) {
        this.id = id;
        this.token = token;
        this.accountId = accountId;
        this.tokenType = tokenType;
        this.expiryDate = expiryDate;
        this.used = used;
    }

    public UUID getId() { return id; }
    public String getToken() { return token; }
    public UUID getAccountId() { return accountId; }
    public String getTokenType() { return tokenType; }
    public Instant getExpiryDate() { return expiryDate; }
    public boolean isUsed() { return used; }
}
