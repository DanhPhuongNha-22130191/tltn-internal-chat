package com.tltn.identity.domain.valueobjects;

import com.tltn.identity.domain.seedworks.ValueObject;
import java.util.Collections;

public class TokenString extends ValueObject {

    private final String value;

    public TokenString(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
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
