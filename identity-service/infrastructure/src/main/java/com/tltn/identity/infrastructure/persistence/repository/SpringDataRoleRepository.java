package com.tltn.identity.infrastructure.persistence.repository;

import com.tltn.identity.infrastructure.persistence.entity.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataRoleRepository extends JpaRepository<RoleJpaEntity, UUID> {
    Optional<RoleJpaEntity> findByRoleKey(String roleKey);
    Optional<RoleJpaEntity> findByName(String name);
    boolean existsByRoleKey(String roleKey);
}
