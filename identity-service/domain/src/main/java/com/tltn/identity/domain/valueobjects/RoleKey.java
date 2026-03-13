package com.tltn.identity.domain.valueobjects;

import com.tltn.identity.domain.seedworks.ValueObject;
import java.util.Collections;

public class RoleKey extends ValueObject {

    private final String value;

    public RoleKey(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Role key cannot be null or empty");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return Collections.singletonList(value);
    }
}
