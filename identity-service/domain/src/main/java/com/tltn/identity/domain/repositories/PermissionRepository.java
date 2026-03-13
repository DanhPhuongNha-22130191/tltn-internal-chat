package com.tltn.identity.domain.repositories;

import com.tltn.identity.domain.entities.Permission;
import com.tltn.identity.domain.valueobjects.PermissionKey;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository {

    Optional<Permission> findById(UUID id);

    Optional<Permission> findByKey(PermissionKey key);

    List<Permission> findAll();

    void save(Permission permission);

    void delete(Permission permission);
    
    boolean existsByKey(PermissionKey key);
}
