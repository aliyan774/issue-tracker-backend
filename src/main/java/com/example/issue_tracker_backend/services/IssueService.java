package com.example.issue_tracker_backend.services;

import com.example.issue_tracker_backend.dto.IssueRequest;
import com.example.issue_tracker_backend.dto.IssueResponse;
import com.example.issue_tracker_backend.entity.ActivityLog;
import com.example.issue_tracker_backend.entity.Issue;
import com.example.issue_tracker_backend.entity.Project;
import com.example.issue_tracker_backend.entity.User;
import com.example.issue_tracker_backend.exception.ResourceNotFoundException;
import com.example.issue_tracker_backend.exception.UnauthorizedException;
import com.example.issue_tracker_backend.repository.IssueRepository;
import com.example.issue_tracker_backend.repository.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ActivityLogService activityLogService;
    private final ObjectMapper objectMapper;
    
    public IssueService(IssueRepository issueRepository, ProjectRepository projectRepository, 
      UserService userService, ActivityLogService activityLogService, 
      ObjectMapper objectMapper) {
      this.issueRepository = issueRepository;
      this.projectRepository = projectRepository;
      this.userService = userService;
      this.activityLogService = activityLogService;
      this.objectMapper = objectMapper;
    }

    @Transactional
    public IssueResponse createIssue(IssueRequest request, Long currentUserId) {

      Project project = projectRepository.findById(request.getProjectId())
        .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

      User creator = userService.findUserEntityById(currentUserId);

      Issue issue = new Issue();
      issue.setTitle(request.getTitle());
      issue.setDescription(request.getDescription());
      issue.setStatus(Issue.IssueStatus.valueOf(request.getStatus()));
      issue.setProject(project);
      issue.setCreatedBy(creator);
      issue.setDueDate(request.getDueDate());
      issue.setEstimatedTime(request.getEstimatedTime());

      if (request.getAssignedToId() != null) {
        User assignee = userService.findUserEntityById(request.getAssignedToId());
        issue.setAssignedTo(assignee);
      }

      Issue savedIssue = issueRepository.save(issue);

      activityLogService.logActivity(
        savedIssue,
        creator,
        ActivityLog.ActionType.CREATED,
        null
      );

      return IssueResponse.fromEntity(savedIssue);
    }

    public List<IssueResponse> getAllIssues() {
      return issueRepository.findAll()
        .stream()
        .map(IssueResponse::fromEntity)
        .collect(Collectors.toList());
    }

    public IssueResponse getIssueById(Long id) {
      Issue issue = issueRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Issue not found"));
      return IssueResponse.fromEntity(issue);
    }

    public List<IssueResponse> getIssuesByProject(Long projectId) {
      return issueRepository.findByProjectId(projectId)
        .stream()
        .map(IssueResponse::fromEntity)
        .collect(Collectors.toList());
    }

    public List<IssueResponse> getIssuesByAssignee(Long userId) {
      return issueRepository.findByAssignedToId(userId)
        .stream()
        .map(IssueResponse::fromEntity)
        .collect(Collectors.toList());
    }

    @Transactional
    public IssueResponse updateIssue(Long id, IssueRequest request, Long currentUserId) {

      Issue issue = issueRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Issue not found"));

      User currentUser = userService.findUserEntityById(currentUserId);

      if (!canUpdateIssue(issue, currentUser)) {
        throw new UnauthorizedException("You don't have permission to update this issue");
      }

      Map<String, Object> changes = new HashMap<>();

      if (!Objects.equals(issue.getTitle(), request.getTitle())) {
        changes.put("title", Map.of("old", issue.getTitle(), "new", request.getTitle()));
        issue.setTitle(request.getTitle());
      }

      if (!Objects.equals(issue.getDescription(), request.getDescription())) {
        changes.put("description", Map.of("old", issue.getDescription(), "new", request.getDescription()));
        issue.setDescription(request.getDescription());
      }

      Issue.IssueStatus newStatus = Issue.IssueStatus.valueOf(request.getStatus());
      if (issue.getStatus() != newStatus) {
        changes.put("status", Map.of("old", issue.getStatus(), "new", newStatus));
        issue.setStatus(newStatus);
      }

      if (request.getAssignedToId() != null) {
        User newAssignee = userService.findUserEntityById(request.getAssignedToId());

        if (issue.getAssignedTo() == null ||
          !issue.getAssignedTo().getId().equals(newAssignee.getId())) {

          changes.put("assignedTo", Map.of(
            "old", issue.getAssignedTo() != null ? issue.getAssignedTo().getId() : null,
            "new", newAssignee.getId()
          ));

          issue.setAssignedTo(newAssignee);
        }
      }

      Issue updatedIssue = issueRepository.save(issue);

      if (!changes.isEmpty()) {
        String changesJson = convertChangesToJson(changes);

        ActivityLog.ActionType action =
          changes.containsKey("status")
            ? ActivityLog.ActionType.STATUS_CHANGED
            : ActivityLog.ActionType.UPDATED;

        activityLogService.logActivity(
          updatedIssue,
          currentUser,
          action,
          changesJson
        );
      }

    return IssueResponse.fromEntity(updatedIssue);
  }

  @Transactional
  public void deleteIssue(Long id, Long currentUserId) {

    Issue issue = issueRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Issue not found"));

    User currentUser = userService.findUserEntityById(currentUserId);

    if (!canDeleteIssue(issue, currentUser)) {
      throw new UnauthorizedException("You don't have permission to delete this issue");
    }

    activityLogService.logActivity(
      issue,
      currentUser,
      ActivityLog.ActionType.DELETED,
      null
    );

    issueRepository.delete(issue);
  }

  public List<IssueResponse> searchIssues(Long projectId, String searchTerm) {
    return issueRepository.searchIssues(projectId, searchTerm)
      .stream()
      .map(IssueResponse::fromEntity)
      .collect(Collectors.toList());
  }

  private boolean canUpdateIssue(Issue issue, User user) {
    return user.getRole() == User.UserRole.ADMIN ||
      issue.getCreatedBy().getId().equals(user.getId()) ||
      (issue.getAssignedTo() != null &&
      issue.getAssignedTo().getId().equals(user.getId()));
  }

  private boolean canDeleteIssue(Issue issue, User user) {
    return user.getRole() == User.UserRole.ADMIN ||
      issue.getCreatedBy().getId().equals(user.getId());
  }

  private String convertChangesToJson(Map<String, Object> changes) {
    try {
      return objectMapper.writeValueAsString(changes);
    } catch (Exception e) {
      return "{}";
    }
  }
}
