package com.cycling.stats.services.impl;

import com.cycling.stats.domain.dtos.UserPrincipal;
import com.cycling.stats.domain.entities.Users;
import com.cycling.stats.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(user);
    }
}
