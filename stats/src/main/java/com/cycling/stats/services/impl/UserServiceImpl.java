package com.cycling.stats.services.impl;

import com.cycling.stats.domain.entities.Users;
import com.cycling.stats.repositories.UserRepository;
import com.cycling.stats.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTServiceImpl jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String verify(Users user) {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }

        return  "Failed";
    }

    @Override
    public Optional<Users> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }
}
