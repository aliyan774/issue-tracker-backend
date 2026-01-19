package com.example.issue_tracker_backend.dto;

import com.example.issue_tracker_backend.entity.Issue;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
