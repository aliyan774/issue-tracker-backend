package com.example.issue_tracker_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "issues")
public class Issue {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(length = 5000)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private IssueStatus status;

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne
  @JoinColumn(name = "assigned_to")
  private User assignedTo;

  @ManyToOne
  @JoinColumn(name = "created_by", nullable = false)
  private User createdBy;

  @Column(nullable = false)
  private LocalDateTime dueDate;

  @Column(nullable = false)
  private Integer estimatedTime;

  private Integer completedTime;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
  private Set<ActivityLog> activityLogs;

  public enum IssueStatus {
    NEW, IN_PROGRESS, COMPLETED, BLOCKED
  }
  
  public Issue() {}
  
  public Issue(String title, String description, IssueStatus status, Project project, User createdBy, LocalDateTime dueDate, Integer estimatedTime) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.project = project;
    this.createdBy = createdBy;
    this.dueDate = dueDate;
    this.estimatedTime = estimatedTime;
  }
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  
  public IssueStatus getStatus() { return status; }
  public void setStatus(IssueStatus status) { this.status = status; }
  
  public Project getProject() { return project; }
  public void setProject(Project project) { this.project = project; }
  
  public User getAssignedTo() { return assignedTo; }
  public void setAssignedTo(User assignedTo) { this.assignedTo = assignedTo; }
  
  public User getCreatedBy() { return createdBy; }
  public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
  
  public LocalDateTime getDueDate() { return dueDate; }
  public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
  
  public Integer getEstimatedTime() { return estimatedTime; }
  public void setEstimatedTime(Integer estimatedTime) { this.estimatedTime = estimatedTime; }
  
  public Integer getCompletedTime() { return completedTime; }
  public void setCompletedTime(Integer completedTime) { this.completedTime = completedTime; }
  
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
  
  public LocalDateTime getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
  
  public Set<ActivityLog> getActivityLogs() { return activityLogs; }
  public void setActivityLogs(Set<ActivityLog> activityLogs) { this.activityLogs = activityLogs; }
}
