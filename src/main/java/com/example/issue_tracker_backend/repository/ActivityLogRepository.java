package com.example.issue_tracker_backend.repository;

import com.example.issue_tracker_backend.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
  List<ActivityLog> findByIssueIdOrderByTimestampDesc(Long issueId);
}
