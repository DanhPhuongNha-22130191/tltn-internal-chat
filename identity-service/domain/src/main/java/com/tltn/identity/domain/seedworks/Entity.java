package com.tltn.identity.domain.seedworks;

import java.util.Objects;

public abstract class Entity<TId> {

    private final TId id;

    protected Entity(TId id) {
        this.id = id;
    }

    public TId getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Entity<?> other))
            return false;

        if (id == null)
            return false;

        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), id);
    }
}