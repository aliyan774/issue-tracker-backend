package com.example.issue_tracker_backend.dto;
import com.example.issue_tracker_backend.entity.User;

import java.time.LocalDateTime;

public class UserResponse {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String role;
  private LocalDateTime createdAt;
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  
  public String getRole() { return role; }
  public void setRole(String role) { this.role = role; }
  
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
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

