package com.example.issue_tracker_backend.dto;
import com.example.issue_tracker_backend.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String role;
  private LocalDateTime createdAt;
    
  public static UserResponse fromEntity(User user) {
    UserResponse response = new UserResponse();
    response.setId(user.getId());
    response.setFirstName(user.getFirstName());
    response.setLastName(user.getLastName());
    response.setEmail(user.getEmail());
    response.setRole(user.getRole().toString());
    response.setCreatedAt(user.getCreatedAt());
    return response;
  }
}

