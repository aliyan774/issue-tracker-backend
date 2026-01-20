package com.example.issue_tracker_backend.services;

import com.example.issue_tracker_backend.dto.UserResponse;
import com.example.issue_tracker_backend.entity.User;
import com.example.issue_tracker_backend.exception.ResourceNotFoundException;
import com.example.issue_tracker_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
  private final UserRepository userRepository;
  
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserResponse> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
      .map(UserResponse::fromEntity)
      .collect(Collectors.toList());
  }

  public UserResponse getUserById(Long id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return UserResponse.fromEntity(user);
  }
  
  public User findUserEntityById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
  }
  
}
