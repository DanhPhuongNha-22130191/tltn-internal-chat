package com.tltn.identity.infrastructure.persistence.adapter;

import com.tltn.identity.domain.entities.VerificationToken;
import com.tltn.identity.domain.repositories.VerificationTokenRepository;
import com.tltn.identity.domain.valueobjects.TokenString;
import com.tltn.identity.domain.valueobjects.TokenType;
import com.tltn.identity.infrastructure.persistence.entity.VerificationTokenJpaEntity;
import com.tltn.identity.infrastructure.persistence.repository.SpringDataVerificationTokenRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class VerificationTokenRepositoryAdapter implements VerificationTokenRepository {

    private final SpringDataVerificationTokenRepository jpaRepository;

    public VerificationTokenRepositoryAdapter(SpringDataVerificationTokenRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<VerificationToken> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<VerificationToken> findByToken(TokenString token) {
        return jpaRepository.findByToken(token.getValue()).map(this::toDomain);
    }

    @Override
    public List<VerificationToken> findByAccountIdAndType(UUID accountId, TokenType type) {
        return jpaRepository.findByAccountIdAndTokenType(accountId, type.name()).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(VerificationToken verificationToken) {
        jpaRepository.save(toJpaEntity(verificationToken));
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        jpaRepository.deleteById(verificationToken.getId());
    }

    private VerificationToken toDomain(VerificationTokenJpaEntity entity) {
        return VerificationToken.createWithId(
                entity.getId(),
                new TokenString(entity.getToken()),
                entity.getAccountId(),
                TokenType.valueOf(entity.getTokenType()),
                entity.getExpiryDate(),
                entity.isUsed()
        );
    }

    private VerificationTokenJpaEntity toJpaEntity(VerificationToken domain) {
        return new VerificationTokenJpaEntity(
                domain.getId(),
                domain.getToken().getValue(),
                domain.getAccountId(),
                domain.getType().name(),
                domain.getExpiryDate(),
                domain.isUsed()
        );
    }
}
