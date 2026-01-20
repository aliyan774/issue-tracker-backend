package com.example.issue_tracker_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
  private boolean success;
  private String message;
  private Object data;
  
  public ApiResponse() {
    this.success = false;
    this.message = "";
    this.data = null;
  }
  
  public static ApiResponse success(String message, Object data) {
    return new ApiResponse(true, message, data);
  }
  
  public static ApiResponse error(String message) {
    return new ApiResponse(false, message, null);
  }
}
