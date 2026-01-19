package com.example.issue_tracker_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProjectRequest {
  @NotBlank(message = "Project name is required")
  private String name;

  private String description;

  @NotNull(message = "Organization ID is required")
  private Long organizationId;
}
