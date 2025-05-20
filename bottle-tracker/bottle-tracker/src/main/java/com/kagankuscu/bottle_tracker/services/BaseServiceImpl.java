package com.kagankuscu.bottle_tracker.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    private final JpaRepository<T, ID> repo;

    public BaseServiceImpl(JpaRepository<T, ID> repo) {
        this.repo = repo;
    }

    @Override
    public List<T> findAll() {
        return repo.findAll();
    }
}
