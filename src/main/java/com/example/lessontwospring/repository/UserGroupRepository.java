package com.example.lessontwospring.repository;

import com.example.lessontwospring.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UUID> {
}
