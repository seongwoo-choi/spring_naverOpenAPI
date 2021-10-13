package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

// JPA 역할
public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int index);

    T save(T entity);

    void deleteById(int index);

    List<T> listAll();
}
