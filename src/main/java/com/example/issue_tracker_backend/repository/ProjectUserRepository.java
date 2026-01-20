package com.example.issue_tracker_backend.repository;

import com.example.issue_tracker_backend.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

  List<ProjectUser> findByProjectId(Long projectId);
  List<ProjectUser> findByUserId(Long userId);
  Optional<ProjectUser> findByProjectIdAndUserId(Long projectId, Long userId);

}
