package com.tltn.identity.infrastructure.persistence.repository;

import com.tltn.identity.infrastructure.persistence.entity.PermissionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataPermissionRepository extends JpaRepository<PermissionJpaEntity, UUID> {
    Optional<PermissionJpaEntity> findByPermissionKey(String permissionKey);
    boolean existsByPermissionKey(String permissionKey);
}
