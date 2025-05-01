package com.cycling.stats.services;

import com.cycling.stats.domain.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Users register(Users user);

    String verify(Users user);

    Optional<Users> findUserById(Integer id);

    List<Users> findAll();
}
