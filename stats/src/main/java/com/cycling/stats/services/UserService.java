package com.cycling.stats.services;

import com.cycling.stats.domain.entities.Users;

public interface UserService {

    public Users register(Users user);

    String verify(Users user);
}
