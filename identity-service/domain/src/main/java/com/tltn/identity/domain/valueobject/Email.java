package com.tltn.identity.domain.valueobject;

import com.tltn.identity.domain.core.ValueObject;

import java.util.List;

public class Email extends ValueObject {
    private final String value;

    public Email(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!value.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new IllegalArgumentException("Email is not valid");
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
