package com.tltn.identity.domain.core;

import java.util.Optional;

public interface Repository<T extends AggregateRoot, ID> {
    void save(T aggregateRoot);
    Optional<T> findById(ID id);
    void delete(T aggregateRoot);
}
