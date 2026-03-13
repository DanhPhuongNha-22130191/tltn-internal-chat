package com.tltn.identity.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @Column(nullable = false)
    private UUID accountId;

    @Column(nullable = false)
    private Instant expiryDate;

    @Column(nullable = false)
    private boolean revoked;

    protected RefreshTokenJpaEntity() {}

    public RefreshTokenJpaEntity(UUID id, String token, UUID accountId, Instant expiryDate, boolean revoked) {
        this.id = id;
        this.token = token;
        this.accountId = accountId;
        this.expiryDate = expiryDate;
        this.revoked = revoked;
    }

    public UUID getId() { return id; }
    public String getToken() { return token; }
    public UUID getAccountId() { return accountId; }
    public Instant getExpiryDate() { return expiryDate; }
    public boolean isRevoked() { return revoked; }
}
