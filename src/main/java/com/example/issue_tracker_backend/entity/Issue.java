package com.example.issue_tracker_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Issue {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(length = 5000)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private IssueStatus status;

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne
  @JoinColumn(name = "assigned_to", nullable = false)
  private User assignedTo;

  @ManyToOne
  @JoinColumn(name = "created_by", nullable = false)
  private User createdBy;

  @Column(nullable = false)
  private LocalDateTime dueDate;

  @Column(nullable = false)
  private Integer estimatedTime;

  private Integer completedTime;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
  private Set<ActivityLog> activityLogs;

  public enum IssueStatus {
    NEW, IN_PROGRESS, COMPLETED, BLOCKED
  }
}
