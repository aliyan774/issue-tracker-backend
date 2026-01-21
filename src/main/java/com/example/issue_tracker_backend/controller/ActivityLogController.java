package com.example.issue_tracker_backend.controller;

import com.example.issue_tracker_backend.dto.ApiResponse;
import com.example.issue_tracker_backend.entity.ActivityLog;
import com.example.issue_tracker_backend.services.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityLogController {
    
    private final ActivityLogService activityLogService;
    
    @GetMapping("/issue/{issueId}")
    public ResponseEntity<ApiResponse> getActivityLogs(@PathVariable Long issueId) {
      List<ActivityLog> logs = activityLogService.getActivityLogs(issueId);
      return ResponseEntity.ok(ApiResponse.success("Activity logs retrieved", logs));
    }
}
