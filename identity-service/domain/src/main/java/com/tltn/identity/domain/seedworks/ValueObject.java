package com.tltn.identity.domain.seedworks;

import java.util.Iterator;
import java.util.Objects;

public abstract class ValueObject {

    protected abstract Iterable<Object> getEqualityComponents();

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        ValueObject other = (ValueObject) obj;

        Iterator<Object> thisComponents = getEqualityComponents().iterator();
        Iterator<Object> otherComponents = other.getEqualityComponents().iterator();

        while (thisComponents.hasNext() && otherComponents.hasNext()) {
            if (!Objects.equals(thisComponents.next(), otherComponents.next()))
                return false;
        }

        return !thisComponents.hasNext() && !otherComponents.hasNext();
    }

    @Override
    public int hashCode() {

        int hash = 0;

        for (Object obj : getEqualityComponents()) {
            hash ^= Objects.hashCode(obj);
        }

        return hash;
    }
}
