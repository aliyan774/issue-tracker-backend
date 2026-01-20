package com.example.issue_tracker_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "organizations")
public class Organization {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
  private Set<Project> projects;
  
  public Organization() {}
  
  public Organization(String name) {
    this.name = name;
  }
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
  
  public Set<Project> getProjects() { return projects; }
  public void setProjects(Set<Project> projects) { this.projects = projects; }
}
