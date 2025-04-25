package com.cycling.stats.controller;

import com.cycling.stats.domain.entities.Users;
import com.cycling.stats.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/users/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
    }

    @PostMapping(path = "/users/login")
    public String login(@RequestBody Users user) {
        return userService.verify(user);
    }
}
