package com.tltn.identity.persistence.mappers;

import com.tltn.identity.domain.entities.Permission;
import com.tltn.identity.domain.valueobject.PermissionName;
import com.tltn.identity.persistence.entities.PermissionEntity;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public Permission toDomain(PermissionEntity entity) {
        if (entity == null) return null;
        return new Permission(
            entity.getId(),
            new PermissionName(entity.getName()),
            entity.getDescription()
        );
    }

    public PermissionEntity toEntity(Permission domain) {
        if (domain == null) return null;
        return PermissionEntity.builder()
            .id(domain.getId())
            .name(domain.getName().getValue())
            .description(domain.getDescription())
            .build();
    }
}
