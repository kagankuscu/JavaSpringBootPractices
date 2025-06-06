package com.kagankuscu.relationtable.repositoroies;

import com.kagankuscu.relationtable.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
}
