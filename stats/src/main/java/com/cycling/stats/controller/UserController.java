package com.cycling.stats.controller;

import com.cycling.stats.domain.entities.Users;
import com.cycling.stats.exceptions.ResourceNotFoundException;
import com.cycling.stats.response.ApiResponse;
import com.cycling.stats.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "${api.prefix}/users")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse> getUsers() {
        return new ResponseEntity<>(
                new ApiResponse("success", userService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable("id") Integer id) {
        return userService
                .findUserById(id)
                .map(user -> new ResponseEntity<>(
                        new ApiResponse("success", user),
                        HttpStatus.OK)
                )
                .orElseThrow(() -> new ResourceNotFoundException("user", id));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse> register(@RequestBody Users user) {
        return new ResponseEntity<>(
                new ApiResponse("success", userService.register(user)),
                HttpStatus.OK
        );
    }

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse> login(@RequestBody Users user) {
        return new ResponseEntity<>(
                new ApiResponse("success", userService.verify(user)),
                HttpStatus.OK
        );
    }
}
