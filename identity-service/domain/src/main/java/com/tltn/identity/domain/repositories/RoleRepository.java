package com.tltn.identity.domain.repositories;

import com.tltn.identity.domain.core.Repository;
import com.tltn.identity.domain.entities.Role;
import com.tltn.identity.domain.valueobject.RoleName;
import java.util.UUID;
import java.util.Optional;

public interface RoleRepository extends Repository<Role, UUID> {
    Optional<Role> findByName(RoleName name);
}
