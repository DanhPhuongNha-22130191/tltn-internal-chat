package com.tltn.identity.domain.entities;

import com.tltn.identity.domain.seedworks.Entity;
import com.tltn.identity.domain.seedworks.IAggregateRoot;
import com.tltn.identity.domain.valueobjects.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Role extends Entity<UUID> implements IAggregateRoot {

    private RoleName name;
    private final RoleKey key;
    private RoleType type;
    private RoleStatus status;
    private String description;
    private Level level;
    private final Set<PermissionKey> permissionKeys;

    // Default constructor for ORM frameworks
    protected Role() {
        super(null);
        this.key = null;
        this.permissionKeys = new HashSet<>();
    }

    private Role(UUID id, RoleName name, RoleKey key, RoleType type, RoleStatus status, String description, Level level, Set<PermissionKey> permissionKeys) {
        super(id);
        this.name = name;
        this.key = key;
        this.type = type;
        this.status = status;
        this.description = description;
        this.level = level;
        this.permissionKeys = permissionKeys != null ? new HashSet<>(permissionKeys) : new HashSet<>();
    }

    public static Role create(RoleName name, RoleKey key, RoleType type, RoleStatus status, String description, Level level) {
        return new Role(UUID.randomUUID(), name, key, type, status, description, level, new HashSet<>());
    }

    public static Role createWithId(UUID id, RoleName name, RoleKey key, RoleType type, RoleStatus status, String description, Level level, Set<PermissionKey> permissionKeys) {
        return new Role(id, name, key, type, status, description, level, permissionKeys);
    }

    public RoleName getName() {
        return name;
    }

    public RoleKey getKey() {
        return key;
    }

    public RoleType getType() {
        return type;
    }

    public RoleStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Level getLevel() {
        return level;
    }

    public Set<PermissionKey> getPermissionKeys() {
        return Collections.unmodifiableSet(permissionKeys);
    }
    
    public void addPermission(PermissionKey permissionKey) {
        if (permissionKey != null) {
            this.permissionKeys.add(permissionKey);
        }
    }

    public void removePermission(PermissionKey permissionKey) {
        if (permissionKey != null) {
            this.permissionKeys.remove(permissionKey);
        }
    }
}
