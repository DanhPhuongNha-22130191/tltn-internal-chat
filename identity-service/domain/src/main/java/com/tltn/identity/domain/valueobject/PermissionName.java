package com.tltn.identity.domain.valueobject;

import com.tltn.identity.domain.core.ValueObject;
import java.util.List;

public class PermissionName extends ValueObject {
    private final String value;

    public PermissionName(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Permission name cannot be null or empty");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    protected List<Object> getEqualityComponents() {
        return List.of(value);
    }
}
