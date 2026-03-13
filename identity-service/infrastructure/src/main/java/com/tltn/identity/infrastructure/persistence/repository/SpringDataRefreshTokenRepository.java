package com.tltn.identity.infrastructure.persistence.repository;

import com.tltn.identity.infrastructure.persistence.entity.RefreshTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataRefreshTokenRepository extends JpaRepository<RefreshTokenJpaEntity, UUID> {
    Optional<RefreshTokenJpaEntity> findByToken(String token);
    List<RefreshTokenJpaEntity> findByAccountId(UUID accountId);
    void deleteByAccountId(UUID accountId);
}
