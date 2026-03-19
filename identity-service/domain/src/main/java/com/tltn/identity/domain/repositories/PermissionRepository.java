package com.tltn.identity.domain.repositories;

import com.tltn.identity.domain.core.Repository;
import com.tltn.identity.domain.entities.Permission;
import com.tltn.identity.domain.valueobject.PermissionName;
import java.util.UUID;
import java.util.Optional;

public interface PermissionRepository extends Repository<Permission, UUID> {
    Optional<Permission> findByName(PermissionName name);
}
