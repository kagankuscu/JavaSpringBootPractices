package com.cycling.stats.repositories;

import com.cycling.stats.domain.entities.Gear;
import com.cycling.stats.domain.entities.Rider;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GearRepository extends JpaRepository<Gear, Long> {
    @Query("SELECT g FROM Gear g WHERE g.deleted = false")
    List<Gear> findAllNotDeleted();

    @Modifying
    @Transactional
    @Query("UPDATE Gear g SET g.deleted = true WHERE g.id=:id")
    void softDelete(Long id);
}
