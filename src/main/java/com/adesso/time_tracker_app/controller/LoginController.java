package com.adesso.time_tracker_app.controller;


import com.adesso.time_tracker_app.dto.AuthRequestDTO;
import com.adesso.time_tracker_app.dto.AuthenticationResponseDTO;
import com.adesso.time_tracker_app.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthRequestDTO request) {
        AuthenticationResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
