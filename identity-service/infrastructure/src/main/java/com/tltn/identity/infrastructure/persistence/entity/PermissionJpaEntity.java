package com.tltn.identity.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.UUID;

@Entity
@Table(name = "permissions")
public class PermissionJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String permissionKey;

    @Column(nullable = false)
    private String name;

    private String description;

    protected PermissionJpaEntity() {}

    public PermissionJpaEntity(UUID id, String permissionKey, String name, String description) {
        this.id = id;
        this.permissionKey = permissionKey;
        this.name = name;
        this.description = description;
    }

    public UUID getId() { return id; }
    public String getPermissionKey() { return permissionKey; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}
