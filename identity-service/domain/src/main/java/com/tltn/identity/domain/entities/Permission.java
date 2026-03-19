package com.tltn.identity.domain.entities;

import com.tltn.identity.domain.core.AggregateRoot;
import com.tltn.identity.domain.core.Entity;
import com.tltn.identity.domain.valueobject.PermissionName;

import java.util.UUID;

public class Permission extends Entity<UUID> implements AggregateRoot {
    private PermissionName name;
    private String description;

    public Permission(UUID uuid, PermissionName name, String description) {
        super(uuid);
        this.name = name;
        this.description = description;
    }

    public PermissionName getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
