package com.example.issue_tracker_backend.repository;

import com.example.issue_tracker_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository {
  Optional findByEmail(String email);
  boolean existsByEmail(String email);
}
