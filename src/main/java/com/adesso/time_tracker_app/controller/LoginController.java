package com.adesso.time_tracker_app.controller;


import com.adesso.time_tracker_app.dto.AuthRequestDTO;
import com.adesso.time_tracker_app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        String token = authService.login(request);
        return ResponseEntity.ok().body(java.util.Map.of("token", token));
    }
}
