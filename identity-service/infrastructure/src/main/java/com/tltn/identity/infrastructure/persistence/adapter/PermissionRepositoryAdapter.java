package com.tltn.identity.infrastructure.persistence.adapter;

import com.tltn.identity.domain.entities.Permission;
import com.tltn.identity.domain.repositories.PermissionRepository;
import com.tltn.identity.domain.valueobjects.PermissionKey;
import com.tltn.identity.domain.valueobjects.PermissionName;
import com.tltn.identity.infrastructure.persistence.entity.PermissionJpaEntity;
import com.tltn.identity.infrastructure.persistence.repository.SpringDataPermissionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PermissionRepositoryAdapter implements PermissionRepository {

    private final SpringDataPermissionRepository jpaRepository;

    public PermissionRepositoryAdapter(SpringDataPermissionRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Permission> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Permission> findByKey(PermissionKey key) {
        return jpaRepository.findByPermissionKey(key.getValue()).map(this::toDomain);
    }

    @Override
    public List<Permission> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Permission permission) {
        jpaRepository.save(toJpaEntity(permission));
    }

    @Override
    public void delete(Permission permission) {
        jpaRepository.deleteById(permission.getId());
    }

    @Override
    public boolean existsByKey(PermissionKey key) {
        return jpaRepository.existsByPermissionKey(key.getValue());
    }

    private Permission toDomain(PermissionJpaEntity entity) {
        return Permission.createWithId(
                entity.getId(),
                new PermissionName(entity.getName()),
                new PermissionKey(entity.getPermissionKey()),
                entity.getDescription()
        );
    }

    private PermissionJpaEntity toJpaEntity(Permission domain) {
        return new PermissionJpaEntity(
                domain.getId(),
                domain.getKey().getValue(),
                domain.getName().getValue(),
                domain.getDescription()
        );
    }
}
