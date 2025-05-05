package com.adesso.time_tracker_app.service;

import com.adesso.time_tracker_app.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository repo) {
        this.userRepository = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user entity from the database
        com.adesso.time_tracker_app.entity.User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Get user roles from the entity
        var authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .toList();

        // Return a Spring Security User with roles
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
