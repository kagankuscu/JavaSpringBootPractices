package com.kagankuscu.bottle_tracker.repositories;

import com.kagankuscu.bottle_tracker.models.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BottleRepository extends JpaRepository<Bottle, Long> {
}
