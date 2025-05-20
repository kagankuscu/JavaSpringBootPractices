package com.kagankuscu.bottle_tracker.services;

import com.kagankuscu.bottle_tracker.models.Bottle;
import com.kagankuscu.bottle_tracker.repositories.BottleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BottleServiceImpl extends BaseServiceImpl<Bottle, Long> implements BottleService {
    private final BottleRepository repository;

    public BottleServiceImpl(BottleRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
