package com.kagankuscu.relationtable.controllers;

import com.kagankuscu.relationtable.models.AppUser;
import com.kagankuscu.relationtable.repositoroies.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    @PostMapping("/create")
    public AppUser create(@RequestBody AppUser appUser) {
        return userRepository.save(appUser);
    }

    @GetMapping("/list")
    public List<AppUser> list() {
        return userRepository.findAll();
    }
}
