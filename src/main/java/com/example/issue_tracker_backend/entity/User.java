package com.example.issue_tracker_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false , unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserRole role;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "user")
  private Set<ProjectUser> projectUsers;

  @OneToMany(mappedBy = "createdBy")
  private Set<Issue> createdIssues;

  @OneToMany(mappedBy = "assignedTo")
  private Set<Issue> assignedIssues;

  public enum UserRole {
    ADMIN, USER
  }
  
  public User() {}
  
  public User(String firstName, String lastName, String email, String password, UserRole role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
  }
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
  
  public UserRole getRole() { return role; }
  public void setRole(UserRole role) { this.role = role; }
  
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
  
  public Set<ProjectUser> getProjectUsers() { return projectUsers; }
  public void setProjectUsers(Set<ProjectUser> projectUsers) { this.projectUsers = projectUsers; }
  
  public Set<Issue> getCreatedIssues() { return createdIssues; }
  public void setCreatedIssues(Set<Issue> createdIssues) { this.createdIssues = createdIssues; }
  
  public Set<Issue> getAssignedIssues() { return assignedIssues; }
  public void setAssignedIssues(Set<Issue> assignedIssues) { this.assignedIssues = assignedIssues; }

}
