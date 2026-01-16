package com.example.issue_tracker_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    CREATED,
    UPDATED,
    COMMENTED,
    STATUS_CHANGED,
  }

}
