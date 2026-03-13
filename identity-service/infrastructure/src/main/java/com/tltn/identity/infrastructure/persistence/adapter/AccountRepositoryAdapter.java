package com.tltn.identity.infrastructure.persistence.adapter;

import com.tltn.identity.domain.entities.Account;
import com.tltn.identity.domain.repositories.AccountRepository;
import com.tltn.identity.domain.valueobjects.Email;
import com.tltn.identity.domain.valueobjects.Password;
import com.tltn.identity.domain.valueobjects.RoleKey;
import com.tltn.identity.domain.valueobjects.Username;
import com.tltn.identity.infrastructure.persistence.entity.AccountJpaEntity;
import com.tltn.identity.infrastructure.persistence.repository.SpringDataAccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AccountRepositoryAdapter implements AccountRepository {

    private final SpringDataAccountRepository jpaRepository;

    public AccountRepositoryAdapter(SpringDataAccountRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Account> findByUsername(Username username) {
        return jpaRepository.findByUsername(username.getValue()).map(this::toDomain);
    }

    @Override
    public Optional<Account> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.getValue()).map(this::toDomain);
    }

    @Override
    public void save(Account account) {
        jpaRepository.save(toJpaEntity(account));
    }

    @Override
    public void delete(Account account) {
        jpaRepository.deleteById(account.getId());
    }

    @Override
    public boolean existsByUsername(Username username) {
        return jpaRepository.existsByUsername(username.getValue());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.getValue());
    }

    private Account toDomain(AccountJpaEntity entity) {
        Set<RoleKey> roleKeys = entity.getRoleKeys().stream()
                .map(RoleKey::new)
                .collect(Collectors.toSet());

        return Account.createWithId(
                entity.getId(),
                new Username(entity.getUsername()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                entity.isActive(),
                entity.isDeleted(),
                roleKeys
        );
    }

    private AccountJpaEntity toJpaEntity(Account domain) {
        Set<String> roleKeys = domain.getRoleKeys().stream()
                .map(RoleKey::getValue)
                .collect(Collectors.toSet());

        return new AccountJpaEntity(
                domain.getId(),
                domain.getUsername().getValue(),
                domain.getEmail().getValue(),
                domain.getPassword().getValue(),
                domain.isActive(),
                domain.isDeleted(),
                roleKeys
        );
    }
}
