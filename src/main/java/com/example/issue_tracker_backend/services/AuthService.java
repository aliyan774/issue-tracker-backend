package com.example.issue_tracker_backend.services;

import com.example.issue_tracker_backend.dto.LoginRequest;
import com.example.issue_tracker_backend.dto.SignupRequest;
import com.example.issue_tracker_backend.dto.JwtResponse;
import com.example.issue_tracker_backend.dto.UserResponse;
import com.example.issue_tracker_backend.entity.User;
import com.example.issue_tracker_backend.exception.UnauthorizedException;
import com.example.issue_tracker_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  
  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }
  
  public JwtResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
      .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));
    
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new UnauthorizedException("Invalid email or password");
    }
    
    String token = jwtService.generateToken(
      user.getEmail(), 
      user.getId(), 
      user.getRole().toString()
    );
    
    return new JwtResponse(token, UserResponse.fromEntity(user));
  }
    
  public JwtResponse signup(SignupRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("Email already exists");
    }
    
    User user = new User();
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(User.UserRole.valueOf(request.getRole().toUpperCase()));
    
    User savedUser = userRepository.save(user);
    
    String token = jwtService.generateToken(
      savedUser.getEmail(), 
      savedUser.getId(), 
      savedUser.getRole().toString()
    );
    
    return new JwtResponse(token, UserResponse.fromEntity(savedUser));
  }
}
