package com.example.lessontwospring.repository;

import com.example.lessontwospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByPassportNumber(String passportNumber);
    List<User> findByFirstNameIs(String firstName);
    List<User> findByAgeLessThan(int age);
}
