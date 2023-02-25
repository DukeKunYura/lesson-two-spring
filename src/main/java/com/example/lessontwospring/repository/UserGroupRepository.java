package com.example.lessontwospring.repository;

import com.example.lessontwospring.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UUID> {
    @Query("select group from UserGroup group join fetch group.users where group.id = :groupId")
    Optional<UserGroup> findGroupById(UUID groupId);
}
