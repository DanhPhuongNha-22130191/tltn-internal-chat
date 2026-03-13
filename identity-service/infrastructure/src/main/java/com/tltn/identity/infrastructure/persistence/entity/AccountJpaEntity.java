package com.tltn.identity.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean deleted = false;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "role_key")
    private Set<String> roleKeys = new HashSet<>();

    protected AccountJpaEntity() {}

    public AccountJpaEntity(UUID id, String username, String email, String password, boolean active, boolean deleted, Set<String> roleKeys) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.deleted = deleted;
        this.roleKeys = roleKeys != null ? roleKeys : new HashSet<>();
    }

    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isActive() { return active; }
    public boolean isDeleted() { return deleted; }
    public Set<String> getRoleKeys() { return roleKeys; }
}
