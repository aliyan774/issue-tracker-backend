package com.example.issue_tracker_backend.services;

import com.example.issue_tracker_backend.entity.ActivityLog;
import com.example.issue_tracker_backend.entity.Issue;
import com.example.issue_tracker_backend.entity.User;
import com.example.issue_tracker_backend.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogService {
  
  private final ActivityLogRepository activityLogRepository;
  
  public ActivityLogService(ActivityLogRepository activityLogRepository) {
    this.activityLogRepository = activityLogRepository;
  }

  public void logActivity(Issue issue, User user, ActivityLog.ActionType action, String changes) {
    ActivityLog log = new ActivityLog();
    log.setIssue(issue);
    log.setUser(user);
    log.setAction(action);
    log.setChanges(changes);
    activityLogRepository.save(log);
  }
  
  public List<ActivityLog> getActivityLogs(Long issueId) {
    return activityLogRepository.findByIssueIdOrderByTimestampDesc(issueId);
  }

}
