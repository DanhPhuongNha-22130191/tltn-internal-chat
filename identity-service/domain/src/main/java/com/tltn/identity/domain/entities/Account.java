package com.tltn.identity.domain.entities;

import com.tltn.identity.domain.core.AggregateRoot;
import com.tltn.identity.domain.core.Entity;
import com.tltn.identity.domain.valueobject.Email;
import com.tltn.identity.domain.valueobject.Name;
import com.tltn.identity.domain.valueobject.Password;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class Account extends Entity<UUID> implements AggregateRoot {
    private Name name;
    private Email email;
    private Password password;
    private boolean active;
    private boolean isDeleted;
    private Set<Role> roles;

    protected Account() {
        super(null); // Warning: Protected constructor for frameworks might need a way to set ID later if using standard JPA
    }

    private Account(UUID uuid, Name name, Email email, Password password, boolean active, boolean isDeleted, Set<Role> roles) {
        super(uuid);
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.isDeleted = isDeleted;
        this.roles = roles;
    }

    private Account(UUID uuid, Name name, Email email, Password password) {
        this(uuid, name, email, password, false, false, new HashSet<>());
    }

    public Name getName() {
        return name;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public static Account create(Name name, Email email, Password password) {
        return new Account(UUID.randomUUID(), name, email, password);
    }

    public static Account restore(UUID uuid, Name name, Email email, Password password, boolean active, boolean isDeleted, Set<Role> roles) {
        return new Account(uuid, name, email, password, active, isDeleted, roles);
    }

    public void delete() {
        this.isDeleted = true;
        deactivate();
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void updatePassword(Password newPassword) {
        this.password = newPassword;
    }

    public void updateName(Name newName) {
        this.name = newName;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

}
