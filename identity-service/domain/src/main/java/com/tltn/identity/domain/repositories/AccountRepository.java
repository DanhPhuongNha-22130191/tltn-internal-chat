package com.tltn.identity.domain.repositories;

import com.tltn.identity.domain.entities.Account;
import com.tltn.identity.domain.valueobjects.Email;
import com.tltn.identity.domain.valueobjects.Username;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    
    Optional<Account> findById(UUID id);
    
    Optional<Account> findByUsername(Username username);
    
    Optional<Account> findByEmail(Email email);
    
    void save(Account account);
    
    void delete(Account account);
    
    boolean existsByUsername(Username username);
    
    boolean existsByEmail(Email email);
}
