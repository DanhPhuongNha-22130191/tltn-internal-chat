package com.tltn.identity.domain.entities;

import com.tltn.identity.domain.core.AggregateRoot;
import com.tltn.identity.domain.core.Entity;
import com.tltn.identity.domain.valueobject.RoleLevel;
import com.tltn.identity.domain.valueobject.RoleName;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Role extends Entity<UUID> implements AggregateRoot {
    private RoleName name;
    private String description;
    private RoleLevel level;
    private boolean isDeleted;
    private boolean active;
    private Set<Permission> permissions;

    private Role(UUID uuid, RoleName name, String description, RoleLevel level, boolean isDeleted, boolean active, Set<Permission> permissions) {
        super(uuid);
        this.name = name;
        this.description = description;
        this.level = level;
        this.isDeleted = isDeleted;
        this.active = active;
        this.permissions = permissions;
    }

    public Role(UUID uuid, RoleName name, RoleLevel level) {
        this(uuid, name, null, level, false, true, new HashSet<>());
    }

    public RoleName getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RoleLevel getLevel() {
        return level;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isActive() {
        return active;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
    }

    public static Role restore(UUID uuid, RoleName name, String description, RoleLevel level, boolean isDeleted, boolean active, Set<Permission> permissions) {
        return new Role(uuid, name, description, level, isDeleted, active, permissions);
    }
}
