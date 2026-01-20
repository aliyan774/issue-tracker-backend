package com.example.issue_tracker_backend.repository;

import com.example.issue_tracker_backend.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
  Optional<Issue> findById(Long id);
  List<Issue> findByProjectId(Long projectId);
  List<Issue> findByAssignedToId(Long userId);
  List<Issue> findByCreatedById(Long userId);

  @Query
  (
    "SELECT i FROM Issue i WHERE i.project.id = :projectId AND (LOWER(i.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')))"
  )
  List<Issue> searchIssues(
    @Param("projectId") Long projectId,
    @Param("searchTerm") String searchTerm
  );
}
