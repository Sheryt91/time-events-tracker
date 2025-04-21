package com.adesso.time_tracker_app.service;

import com.adesso.time_tracker_app.dto.AuthRequestDTO;
import com.adesso.time_tracker_app.dto.AuthenticationResponseDTO;
import com.adesso.time_tracker_app.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    public AuthenticationResponseDTO login(AuthRequestDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Invalid username or password", ex);
        }

        UserDetails user = customUserDetailsService.loadUserByUsername(request.getUsername());
        String token= jwtService.generateToken(user.getUsername());
        return new AuthenticationResponseDTO(token, user.getUsername());
    }
}
