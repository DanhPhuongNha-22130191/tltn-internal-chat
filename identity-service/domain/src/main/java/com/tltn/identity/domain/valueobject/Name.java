package com.tltn.identity.domain.valueobject;

import com.tltn.identity.domain.core.ValueObject;

import java.util.List;

public class Name extends ValueObject {
    private final String value;

    public Name(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
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
