package com.cycling.stats.repositories;

import com.cycling.stats.domain.entities.Team;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t WHERE t.deleted = false")
    List<Team> findAllNotDeleted();

    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.deleted = true WHERE t.id=:id")
    void softDelete(Long id);
}
