package com.tltn.identity.domain.valueobject;

import com.tltn.identity.domain.core.ValueObject;
import java.util.List;

public class RoleLevel extends ValueObject {
    private final int value;

    public RoleLevel(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Role level cannot be negative");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    protected List<Object> getEqualityComponents() {
        return List.of(value);
    }
}
