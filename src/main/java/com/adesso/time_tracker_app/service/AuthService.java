package com.adesso.time_tracker_app.service;

import com.adesso.time_tracker_app.dto.AuthRequestDTO;
import com.adesso.time_tracker_app.dto.AuthenticationResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
            throw new IllegalArgumentException("Invalid username or password", ex);
        }

        // Load user details (including roles)
        UserDetails user = customUserDetailsService.loadUserByUsername(request.getUsername());

        // Extract roles from user details
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // Pass roles as a claim
        Map<String, Object> claims = Map.of("roles", roles);

        // Generate JWT token with roles
        String token = jwtService.generateToken(claims, user.getUsername());

        return new AuthenticationResponseDTO(token, user.getUsername(), roles);
    }
}
