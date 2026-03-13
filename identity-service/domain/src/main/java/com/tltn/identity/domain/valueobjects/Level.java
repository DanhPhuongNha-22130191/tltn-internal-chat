package com.tltn.identity.domain.valueobjects;

import com.tltn.identity.domain.seedworks.ValueObject;
import java.util.Collections;

public class Level extends ValueObject {

    private final int value;

    public Level(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Level must be zero or positive");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return Collections.singletonList(value);
    }
}
