package com.tltn.identity.domain.repositories;

import com.tltn.identity.domain.core.Repository;
import com.tltn.identity.domain.entities.Account;
import java.util.UUID;
import java.util.Optional;
import com.tltn.identity.domain.valueobject.Email;

public interface AccountRepository extends Repository<Account, UUID> {
    Optional<Account> findByEmail(Email email);
}
