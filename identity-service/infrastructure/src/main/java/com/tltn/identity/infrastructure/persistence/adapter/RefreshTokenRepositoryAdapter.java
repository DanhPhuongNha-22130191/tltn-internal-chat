package com.tltn.identity.infrastructure.persistence.adapter;

import com.tltn.identity.domain.entities.RefreshToken;
import com.tltn.identity.domain.repositories.RefreshTokenRepository;
import com.tltn.identity.domain.valueobjects.TokenString;
import com.tltn.identity.infrastructure.persistence.entity.RefreshTokenJpaEntity;
import com.tltn.identity.infrastructure.persistence.repository.SpringDataRefreshTokenRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {

    private final SpringDataRefreshTokenRepository jpaRepository;

    public RefreshTokenRepositoryAdapter(SpringDataRefreshTokenRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<RefreshToken> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<RefreshToken> findByToken(TokenString token) {
        return jpaRepository.findByToken(token.getValue()).map(this::toDomain);
    }

    @Override
    public List<RefreshToken> findByAccountId(UUID accountId) {
        return jpaRepository.findByAccountId(accountId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(RefreshToken refreshToken) {
        jpaRepository.save(toJpaEntity(refreshToken));
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        jpaRepository.deleteById(refreshToken.getId());
    }

    @Override
    public void revokeAllByAccountId(UUID accountId) {
        // Find existing and revoke them effectively
        List<RefreshTokenJpaEntity> tokens = jpaRepository.findByAccountId(accountId);
        for(RefreshTokenJpaEntity token : tokens) {
            RefreshToken domain = toDomain(token);
            domain.revoke();
            jpaRepository.save(toJpaEntity(domain));
        }
    }

    private RefreshToken toDomain(RefreshTokenJpaEntity entity) {
        return RefreshToken.createWithId(
                entity.getId(),
                new TokenString(entity.getToken()),
                entity.getAccountId(),
                entity.getExpiryDate(),
                entity.isRevoked()
        );
    }

    private RefreshTokenJpaEntity toJpaEntity(RefreshToken domain) {
        return new RefreshTokenJpaEntity(
                domain.getId(),
                domain.getToken().getValue(),
                domain.getAccountId(),
                domain.getExpiryDate(),
                domain.isRevoked()
        );
    }
}
