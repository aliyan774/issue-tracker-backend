package com.example.issue_tracker_backend.dto;

import com.example.issue_tracker_backend.entity.Issue;

import java.time.LocalDateTime;

public class IssueResponse {
  
  private Long id;
  private String title;
  private String description;
  private String state;
  private Long projectId;
  private String projectName;
  private UserResponse createdBy;
  private UserResponse assignedTo;
  private LocalDateTime dueDate;
  private Integer estimatedTime;
  private Integer completedTime;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  
  public String getState() { return state; }
  public void setState(String state) { this.state = state; }
  
  public Long getProjectId() { return projectId; }
  public void setProjectId(Long projectId) { this.projectId = projectId; }
  
  public String getProjectName() { return projectName; }
  public void setProjectName(String projectName) { this.projectName = projectName; }
  
  public UserResponse getCreatedBy() { return createdBy; }
  public void setCreatedBy(UserResponse createdBy) { this.createdBy = createdBy; }
  
  public UserResponse getAssignedTo() { return assignedTo; }
  public void setAssignedTo(UserResponse assignedTo) { this.assignedTo = assignedTo; }
  
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

  public static IssueResponse fromEntity(Issue issue) {
    IssueResponse response = new IssueResponse();
    response.setId(issue.getId());
    response.setTitle(issue.getTitle());
    response.setDescription(issue.getDescription());
    response.setState(issue.getStatus().name());
    response.setProjectId(issue.getProject().getId());
    response.setProjectName(issue.getProject().getName());
    response.setCreatedBy(UserResponse.fromEntity(issue.getCreatedBy()));
    response.setAssignedTo(issue.getAssignedTo() != null ? UserResponse.fromEntity(issue.getAssignedTo()) : null);
    response.setDueDate(issue.getDueDate());
    response.setEstimatedTime(issue.getEstimatedTime());
    response.setCompletedTime(issue.getCompletedTime());
    response.setCreatedAt(issue.getCreatedAt());
    response.setUpdatedAt(issue.getUpdatedAt());
    return response;
  }

}
