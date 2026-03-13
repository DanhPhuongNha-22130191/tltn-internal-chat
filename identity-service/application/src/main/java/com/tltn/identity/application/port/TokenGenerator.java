package com.tltn.identity.application.port;

import com.tltn.identity.domain.entities.Account;

public interface TokenGenerator {

    String generateAccessToken(Account account);

    String generateRefreshToken(Account account);
}
