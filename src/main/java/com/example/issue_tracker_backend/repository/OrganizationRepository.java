package com.example.issue_tracker_backend.repository;

import com.example.issue_tracker_backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository {
  
}
