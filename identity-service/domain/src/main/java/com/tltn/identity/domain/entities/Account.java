package com.tltn.identity.domain.entities;

import com.tltn.identity.domain.seedworks.Entity;
import com.tltn.identity.domain.seedworks.IAggregateRoot;
import com.tltn.identity.domain.valueobjects.Email;
import com.tltn.identity.domain.valueobjects.Password;
import com.tltn.identity.domain.valueobjects.Username;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.tltn.identity.domain.valueobjects.RoleKey;

public class Account extends Entity<UUID> implements IAggregateRoot {

    private Username username;
    private Email email;
    private Password password;
    private boolean active;
    private boolean deleted;
    private Set<RoleKey> roleKeys;

    protected Account() {
        super(null);
    }

    private Account(UUID id, Username username, Email email, Password password, boolean active, boolean deleted, Set<RoleKey> roleKeys) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.deleted = deleted;
        this.roleKeys = roleKeys != null ? new HashSet<>(roleKeys) : new HashSet<>();
    }

    public static Account create(Username username, Email email, Password password) {
        return new Account(
                UUID.randomUUID(),
                username,
                email,
                password,
                false,
                false,
                new HashSet<>()
        );
    }

    public static Account createWithId(UUID id, Username username, Email email, Password password, boolean active, boolean deleted, Set<RoleKey> roleKeys) {
        return new Account(id, username, email, password, active, deleted, roleKeys);
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;

    }

    public boolean isActive() {
        return active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Set<RoleKey> getRoleKeys() {
        return Collections.unmodifiableSet(roleKeys);
    }

    public void activate() {
        if (this.active) {
            throw new IllegalStateException("Account is already active");
        }
        this.active = true;
    }

    public void deactivate() {
        if (!this.active) {
            throw new IllegalStateException("Account is already deactivated");
        }
        this.active = false;
    }

    public void softDelete() {
        if (this.deleted) {
            throw new IllegalStateException("Account is already deleted");
        }
        this.active = false;
        this.deleted = true;
    }

    public void changePassword(Password newPassword) {
        if (newPassword == null) {
            throw new IllegalArgumentException("New password cannot be null");
        }
        this.password = newPassword;
    }

    public void changeEmail(Email newEmail) {
        if (newEmail == null) {
            throw new IllegalArgumentException("New email cannot be null");
        }
        this.email = newEmail;
    }

    public void grantRole(RoleKey roleKey) {
        if (roleKey == null) {
            throw new IllegalArgumentException("Role key cannot be null");
        }
        this.roleKeys.add(roleKey);
    }

    public void revokeRole(RoleKey roleKey) {
        if (roleKey == null) {
            throw new IllegalArgumentException("Role key cannot be null");
        }
        this.roleKeys.remove(roleKey);
    }
}
