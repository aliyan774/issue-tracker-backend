package com.example.issue_tracker_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class IssueRequest {
  
 @NotBlank(message = "Title is required")
 private String title;

 private String description;

 @NotNull(message = "ProjectId is required")
 private Long projectId;

 @NotNull(message = "Due Date is required")
 private LocalDateTime dueDate;

 @NotNull(message = "Estimated Time is required")
 private Integer estimatedTime;

 private Long assignedToId;

 private String status;
 
 public String getTitle() { return title; }
 public void setTitle(String title) { this.title = title; }
 
 public String getDescription() { return description; }
 public void setDescription(String description) { this.description = description; }
 
 public Long getProjectId() { return projectId; }
 public void setProjectId(Long projectId) { this.projectId = projectId; }
 
 public LocalDateTime getDueDate() { return dueDate; }
 public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
 
 public Integer getEstimatedTime() { return estimatedTime; }
 public void setEstimatedTime(Integer estimatedTime) { this.estimatedTime = estimatedTime; }
 
 public Long getAssignedToId() { return assignedToId; }
 public void setAssignedToId(Long assignedToId) { this.assignedToId = assignedToId; }
 
 public String getStatus() { return status; }
 public void setStatus(String status) { this.status = status; }

}
