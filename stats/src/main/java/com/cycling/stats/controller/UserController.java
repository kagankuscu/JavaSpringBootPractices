package com.cycling.stats.controller;

import com.cycling.stats.domain.entities.Users;
import com.cycling.stats.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "${api.prefix}")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users")
    public List<Users> getUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<Users> findUserByid(@PathVariable("id") Integer id) {
        return userService
                .findUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/users/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
    }

    @PostMapping(path = "/users/login")
    public String login(@RequestBody Users user) {
        return userService.verify(user);
    }
}
