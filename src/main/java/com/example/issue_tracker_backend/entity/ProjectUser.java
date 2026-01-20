package com.example.issue_tracker_backend.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "project_users")
public class ProjectUser implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProjectRole role;

  public enum ProjectRole {
    DEVELOPER,
    MANAGER,
    VIEWER
  }
  
  public ProjectUser() {}
  
  public ProjectUser(Project project, User user, ProjectRole role) {
    this.project = project;
    this.user = user;
    this.role = role;
  }
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public Project getProject() { return project; }
  public void setProject(Project project) { this.project = project; }
  
  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }
  
  public ProjectRole getRole() { return role; }
  public void setRole(ProjectRole role) { this.role = role; }
}
