package com.example.issue_tracker_backend.repository;

import com.example.issue_tracker_backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
  
}
