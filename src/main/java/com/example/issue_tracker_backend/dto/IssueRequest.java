package com.example.issue_tracker_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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

 private long assignedToId;

 private String status;

}
