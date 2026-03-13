package com.tltn.identity.domain.repositories;

import com.tltn.identity.domain.entities.RefreshToken;
import com.tltn.identity.domain.valueobjects.TokenString;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository {

    Optional<RefreshToken> findById(UUID id);

    Optional<RefreshToken> findByToken(TokenString token);

    List<RefreshToken> findByAccountId(UUID accountId);

    void save(RefreshToken refreshToken);

    void delete(RefreshToken refreshToken);

    void revokeAllByAccountId(UUID accountId);
}
