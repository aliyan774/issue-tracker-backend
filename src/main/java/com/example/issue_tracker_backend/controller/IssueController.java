package com.example.issue_tracker_backend.controller;

import com.example.issue_tracker_backend.dto.ApiResponse;
import com.example.issue_tracker_backend.dto.IssueRequest;
import com.example.issue_tracker_backend.dto.IssueResponse;
import com.example.issue_tracker_backend.services.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class IssueController {
    
    private final IssueService issueService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> createIssue(
            @Valid @RequestBody IssueRequest request,
            Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        IssueResponse issue = issueService.createIssue(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Issue created successfully", issue));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse> getAllIssues() {
        List<IssueResponse> issues = issueService.getAllIssues();
        return ResponseEntity.ok(ApiResponse.success("Issues retrieved successfully", issues));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getIssueById(@PathVariable Long id) {
        IssueResponse issue = issueService.getIssueById(id);
        return ResponseEntity.ok(ApiResponse.success("Issue retrieved successfully", issue));
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse> getIssuesByProject(@PathVariable Long projectId) {
        List<IssueResponse> issues = issueService.getIssuesByProject(projectId);
        return ResponseEntity.ok(ApiResponse.success("Issues retrieved successfully", issues));
    }
    
    @GetMapping("/assigned/{userId}")
    public ResponseEntity<ApiResponse> getIssuesByAssignee(@PathVariable Long userId) {
        List<IssueResponse> issues = issueService.getIssuesByAssignee(userId);
        return ResponseEntity.ok(ApiResponse.success("Issues retrieved successfully", issues));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateIssue(
            @PathVariable Long id,
            @Valid @RequestBody IssueRequest request,
            Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        IssueResponse issue = issueService.updateIssue(id, request, userId);
        return ResponseEntity.ok(ApiResponse.success("Issue updated successfully", issue));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteIssue(
            @PathVariable Long id,
            Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        issueService.deleteIssue(id, userId);
        return ResponseEntity.ok(ApiResponse.success("Issue deleted successfully", null));
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchIssues(
            @RequestParam Long projectId,
            @RequestParam String q) {
        List<IssueResponse> issues = issueService.searchIssues(projectId, q);
        return ResponseEntity.ok(ApiResponse.success("Search completed", issues));
    }
}