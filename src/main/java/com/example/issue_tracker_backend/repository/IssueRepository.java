package com.example.issue_tracker_backend.repository;

import com.example.issue_tracker_backend.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository {
  Optional findById(Long id);
  List findByProjectId(Long projectId);
  List findByAssignedToId(Long userId);
  List findByCreatedById(Long userId);

  @Query
  (
    "SELECT i FROM Issue i WHERE i.project.id = :projectId AND i.issue.id = :issueId" +
    " AND (LOWER(i.title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))" 
  )
  List searchIssues(
    @Param("projectId") Long projectId,
    @Param("issueId") Long issueId,
    @Param("searchTerm") String searchTerm
  );
}
