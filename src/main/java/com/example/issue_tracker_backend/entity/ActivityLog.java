package com.example.issue_tracker_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "issue_id", nullable = false)
  private Issue issue;
  
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ActionType action;

  @Column(length = 5000)
  private String changes;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime timestamp;
  
  public enum ActionType {
    CREATED, UPDATED, DELETED, ASSIGNED, STATUS_CHANGED
  }
  
  public ActivityLog() {}
  
  public ActivityLog(Issue issue, User user, ActionType action, String changes) {
    this.issue = issue;
    this.user = user;
    this.action = action;
    this.changes = changes;
  }
  
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public Issue getIssue() { return issue; }
  public void setIssue(Issue issue) { this.issue = issue; }
  
  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }
  
  public ActionType getAction() { return action; }
  public void setAction(ActionType action) { this.action = action; }
  
  public String getChanges() { return changes; }
  public void setChanges(String changes) { this.changes = changes; }
  
  public LocalDateTime getTimestamp() { return timestamp; }
  public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
