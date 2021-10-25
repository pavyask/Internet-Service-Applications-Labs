package com.pavelyasko.aui.lab1.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {
    Optional<E> find(K id);

    List<E> findAll();

    void save(E entity);

    void delete(E entity);
}
