package com.tltn.identity.domain.repositories;

import com.tltn.identity.domain.entities.VerificationToken;
import com.tltn.identity.domain.valueobjects.TokenString;
import com.tltn.identity.domain.valueobjects.TokenType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VerificationTokenRepository {

    Optional<VerificationToken> findById(UUID id);

    Optional<VerificationToken> findByToken(TokenString token);

    List<VerificationToken> findByAccountIdAndType(UUID accountId, TokenType type);

    void save(VerificationToken verificationToken);

    void delete(VerificationToken verificationToken);
}
