package com.tltn.identity.domain.repositories;

import com.tltn.identity.domain.entities.Role;
import com.tltn.identity.domain.valueobjects.RoleKey;
import com.tltn.identity.domain.valueobjects.RoleName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository {

    Optional<Role> findById(UUID id);

    Optional<Role> findByKey(RoleKey key);

    Optional<Role> findByName(RoleName name);

    List<Role> findAll();

    void save(Role role);

    void delete(Role role);

    boolean existsByKey(RoleKey key);
}
