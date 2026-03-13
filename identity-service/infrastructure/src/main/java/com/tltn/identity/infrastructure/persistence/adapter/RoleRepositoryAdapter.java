package com.tltn.identity.infrastructure.persistence.adapter;

import com.tltn.identity.domain.entities.Role;
import com.tltn.identity.domain.repositories.RoleRepository;
import com.tltn.identity.domain.valueobjects.*;
import com.tltn.identity.infrastructure.persistence.entity.RoleJpaEntity;
import com.tltn.identity.infrastructure.persistence.repository.SpringDataRoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RoleRepositoryAdapter implements RoleRepository {

    private final SpringDataRoleRepository jpaRepository;

    public RoleRepositoryAdapter(SpringDataRoleRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Role> findByKey(RoleKey key) {
        return jpaRepository.findByRoleKey(key.getValue()).map(this::toDomain);
    }

    @Override
    public Optional<Role> findByName(RoleName name) {
        return jpaRepository.findByName(name.getValue()).map(this::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Role role) {
        jpaRepository.save(toJpaEntity(role));
    }

    @Override
    public void delete(Role role) {
        jpaRepository.deleteById(role.getId());
    }

    @Override
    public boolean existsByKey(RoleKey key) {
        return jpaRepository.existsByRoleKey(key.getValue());
    }

    private Role toDomain(RoleJpaEntity entity) {
        Set<PermissionKey> permissionKeys = entity.getPermissionKeys().stream()
                .map(PermissionKey::new)
                .collect(Collectors.toSet());

        return Role.createWithId(
                entity.getId(),
                new RoleName(entity.getName()),
                new RoleKey(entity.getRoleKey()),
                new RoleType(entity.getType()),
                new RoleStatus(entity.getStatus()),
                entity.getDescription(),
                new Level(entity.getLevelValue()),
                permissionKeys
        );
    }

    private RoleJpaEntity toJpaEntity(Role domain) {
        Set<String> permissionKeys = domain.getPermissionKeys().stream()
                .map(PermissionKey::getValue)
                .collect(Collectors.toSet());

        return new RoleJpaEntity(
                domain.getId(),
                domain.getKey().getValue(),
                domain.getName().getValue(),
                domain.getType().getValue(),
                domain.getStatus().getValue(),
                domain.getDescription(),
                domain.getLevel().getValue(),
                permissionKeys
        );
    }
}
