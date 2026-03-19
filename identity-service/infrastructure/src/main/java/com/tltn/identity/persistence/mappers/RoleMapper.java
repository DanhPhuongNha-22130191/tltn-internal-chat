package com.tltn.identity.persistence.mappers;

import com.tltn.identity.domain.entities.Permission;
import com.tltn.identity.domain.entities.Role;
import com.tltn.identity.domain.valueobject.RoleLevel;
import com.tltn.identity.domain.valueobject.RoleName;
import com.tltn.identity.persistence.entities.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final PermissionMapper permissionMapper;

    public Role toDomain(RoleEntity entity) {
        if (entity == null) return null;
        return Role.restore(
            entity.getId(),
            new RoleName(entity.getName()),
            entity.getDescription(),
            new RoleLevel(entity.getLevel()),
            entity.isDeleted(),
            entity.isActive(),
            entity.getPermissions().stream()
                .map(permissionMapper::toDomain)
                .collect(Collectors.toSet())
        );
    }

    public RoleEntity toEntity(Role domain) {
        if (domain == null) return null;
        return RoleEntity.builder()
            .id(domain.getId())
            .name(domain.getName().getValue())
            .description(domain.getDescription())
            .level(domain.getLevel().getValue())
            .isDeleted(domain.isDeleted())
            .active(domain.isActive())
            .permissions(domain.getPermissions().stream()
                .map(permissionMapper::toEntity)
                .collect(Collectors.toSet()))
            .build();
    }
}
