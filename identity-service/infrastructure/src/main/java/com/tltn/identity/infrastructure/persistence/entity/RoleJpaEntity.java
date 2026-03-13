package com.tltn.identity.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class RoleJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String roleKey;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String status;

    private String description;

    @Column(nullable = false)
    private int levelValue; // Cannot use keyword 'level' easily in some DBs

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "permission_key")
    private Set<String> permissionKeys = new HashSet<>();

    protected RoleJpaEntity() {}

    public RoleJpaEntity(UUID id, String roleKey, String name, String type, String status, String description, int levelValue, Set<String> permissionKeys) {
        this.id = id;
        this.roleKey = roleKey;
        this.name = name;
        this.type = type;
        this.status = status;
        this.description = description;
        this.levelValue = levelValue;
        this.permissionKeys = permissionKeys != null ? permissionKeys : new HashSet<>();
    }

    public UUID getId() { return id; }
    public String getRoleKey() { return roleKey; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public String getDescription() { return description; }
    public int getLevelValue() { return levelValue; }
    public Set<String> getPermissionKeys() { return permissionKeys; }
}
