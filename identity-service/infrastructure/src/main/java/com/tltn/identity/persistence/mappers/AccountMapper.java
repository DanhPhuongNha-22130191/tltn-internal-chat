package com.tltn.identity.persistence.mappers;

import com.tltn.identity.domain.entities.Account;
import com.tltn.identity.domain.valueobject.Email;
import com.tltn.identity.domain.valueobject.Name;
import com.tltn.identity.domain.valueobject.Password;
import com.tltn.identity.persistence.entities.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final RoleMapper roleMapper;

    public Account toDomain(AccountEntity entity) {
        if (entity == null) return null;
        return Account.restore(
            entity.getId(),
            new Name(entity.getName()),
            new Email(entity.getEmail()),
            new Password(entity.getPassword()),
            entity.isActive(),
            entity.isDeleted(),
            entity.getRoles().stream()
                .map(roleMapper::toDomain)
                .collect(Collectors.toSet())
        );
    }

    public AccountEntity toEntity(Account domain) {
        if (domain == null) return null;
        return AccountEntity.builder()
            .id(domain.getId())
            .name(domain.getName().getValue())
            .email(domain.getEmail().getValue())
            .password(domain.getPassword().getValue())
            .active(domain.isActive())
            .isDeleted(false) // Assuming new save is not deleted by default if not specified
            .roles(domain.getRoles().stream()
                .map(roleMapper::toEntity)
                .collect(Collectors.toSet()))
            .build();
    }
}
