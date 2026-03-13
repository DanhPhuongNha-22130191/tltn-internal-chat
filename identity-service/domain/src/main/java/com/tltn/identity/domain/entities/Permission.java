package com.tltn.identity.domain.entities;

import com.tltn.identity.domain.seedworks.Entity;
import com.tltn.identity.domain.seedworks.IAggregateRoot;
import com.tltn.identity.domain.valueobjects.PermissionKey;
import com.tltn.identity.domain.valueobjects.PermissionName;

import java.util.UUID;

public class Permission extends Entity<UUID> implements IAggregateRoot {

    private PermissionName name;
    private final PermissionKey key;
    private String description;

    // Default constructor for ORM frameworks
    protected Permission() {
        super(null);
        this.key = null;
    }

    private Permission(UUID id, PermissionName name, PermissionKey key, String description) {
        super(id);
        this.name = name;
        this.key = key;
        this.description = description;
    }

    public static Permission create(PermissionName name, PermissionKey key, String description) {
        return new Permission(UUID.randomUUID(), name, key, description);
    }

    public static Permission createWithId(UUID id, PermissionName name, PermissionKey key, String description) {
        return new Permission(id, name, key, description);
    }

    public PermissionName getName() {
        return name;
    }

    public PermissionKey getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
