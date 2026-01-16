package com.example.issue_tracker_backend.repository;

import com.example.issue_tracker_backend.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository {

  List findByProjectId(Long projectId);
  List findByUserId(Long userId);
  Optional findByProjectIdAndUserId(Long projectId, Long userId);

}
