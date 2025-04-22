package com.cycling.stats.repositories;

import com.cycling.stats.domain.entities.Team;
import com.cycling.stats.domain.entities.TeamContract;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamContractRepository extends JpaRepository<TeamContract, Long> {

    @Query("SELECT tc FROM TeamContract tc WHERE tc.deleted = false")
    List<TeamContract> findAllNotDeleted();

    @Modifying
    @Transactional
    @Query("UPDATE TeamContract tc SET tc.deleted = true WHERE tc.id=:id")
    void softDelete(Long id);
}
