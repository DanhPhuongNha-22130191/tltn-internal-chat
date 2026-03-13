package com.tltn.identity.domain.valueobjects;

import com.tltn.identity.domain.seedworks.ValueObject;
import java.util.Collections;

public class RoleType extends ValueObject {

    private final String value;

    public RoleType(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Role type cannot be null or empty");
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
