package com.tltn.identity.impls;

import com.tltn.identity.domain.entities.Account;
import com.tltn.identity.domain.repositories.AccountRepository;
import com.tltn.identity.domain.valueobject.Email;
import com.tltn.identity.persistence.mappers.AccountMapper;
import com.tltn.identity.persistence.repositories.JpaAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final JpaAccountRepository jpaAccountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<Account> findByEmail(Email email) {
        return jpaAccountRepository.findByEmail(email.getValue())
            .map(accountMapper::toDomain);
    }

    @Override
    public void save(Account account) {
        jpaAccountRepository.save(accountMapper.toEntity(account));
    }

    @Override
    public Optional<Account> findById(UUID uuid) {
        return jpaAccountRepository.findById(uuid)
            .map(accountMapper::toDomain);
    }

    @Override
    public void delete(Account account) {
        jpaAccountRepository.delete(accountMapper.toEntity(account));
    }
}
