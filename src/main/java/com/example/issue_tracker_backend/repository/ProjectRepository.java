package com.example.issue_tracker_backend.repository;

import com.example.issue_tracker_backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
