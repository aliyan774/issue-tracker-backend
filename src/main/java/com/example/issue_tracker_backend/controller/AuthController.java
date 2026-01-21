package com.example.issue_tracker_backend.controller;

import com.example.issue_tracker_backend.dto.ApiResponse;
import com.example.issue_tracker_backend.dto.JwtResponse;
import com.example.issue_tracker_backend.dto.LoginRequest;
import com.example.issue_tracker_backend.dto.SignupRequest;
import com.example.issue_tracker_backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {
        JwtResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignupRequest request) {
        JwtResponse response = authService.signup(request);
        return ResponseEntity.ok(ApiResponse.success("Registration successful", response));
    }
}

