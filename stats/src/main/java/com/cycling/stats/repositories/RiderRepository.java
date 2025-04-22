package com.cycling.stats.repositories;

import com.cycling.stats.domain.entities.Rider;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

    @Query("SELECT r FROM Rider r WHERE r.deleted = false")
    List<Rider> findAllNotDeleted();

    @Modifying
    @Transactional
    @Query("UPDATE Rider r SET r.deleted = true WHERE r.id=:id")
    void softDelete(Long id);
}
