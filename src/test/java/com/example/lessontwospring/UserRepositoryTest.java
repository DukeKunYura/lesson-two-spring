package com.example.lessontwospring;

import com.example.lessontwospring.entity.User;
import com.example.lessontwospring.entity.UserGroup;
import com.example.lessontwospring.repository.UserGroupRepository;
import com.example.lessontwospring.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    private UUID groupOneId, groupTwoId, groupThreeId;
    private UUID stanId, borisId, denId;

    @PostConstruct
    void addUserGroup() {
        User stan = new User();
        stan.setFirstName("Stan");
        stan.setSurname("Brown");
        stan.setAge(45);
        stan.setPassportNumber("676767");
        stan = userRepository.save(stan);
        stanId = stan.getId();

        User boris = new User();
        boris.setFirstName("Boris");
        boris.setSurname("Redfoot");
        boris.setAge(40);
        boris.setPassportNumber("670000");
        boris = userRepository.save(boris);
        borisId = boris.getId();

        User den = new User();
        den.setFirstName("Den");
        den.setSurname("Horsefase");
        den.setAge(30);
        den.setPassportNumber("670009");
        den = userRepository.save(den);
        denId = den.getId();

        UserGroup groupOne = new UserGroup();
        groupOne.setName("One");
        groupOne = userGroupRepository.save(groupOne);
        groupOneId = groupOne.getId();

        UserGroup groupTwo = new UserGroup();
        groupTwo.setName("Two");
        groupTwo = userGroupRepository.save(groupTwo);
        groupTwoId = groupTwo.getId();

        UserGroup groupThree = new UserGroup();
        groupTwo.setName("Three");
        groupTwo = userGroupRepository.save(groupThree);
        groupThreeId = groupThree.getId();

        groupOne.getUsers().add(stan);
        groupOne.getUsers().add(boris);
        groupTwo.getUsers().add(boris);
        groupThree.getUsers().add(boris);
        groupTwo.getUsers().add(den);
        groupOne = userGroupRepository.save(groupOne);
        groupTwo = userGroupRepository.save(groupTwo);

    }

    @Test
    void canAddUsers() {
        User stan = userRepository.findById(stanId).orElseThrow();
        assertNotNull(stan);
        assertFalse(stan.getGroups().isEmpty());
        UserGroup groupOne = userGroupRepository.findById(groupOneId).orElseThrow();
        assertNotNull(groupOne);
        UserGroup groupTwo = userGroupRepository.findById(groupOneId).orElseThrow();
        assertNotNull(groupTwo);
        assertFalse(groupOne.getUsers().isEmpty());
        assertEquals(2, groupOne.getUsers().size());
        assertEquals(2, groupTwo.getUsers().size());

        UserGroup groupThree = userGroupRepository.findById(groupThreeId).orElseThrow();
        assertEquals(borisId, groupThree.getUsers().iterator().next().getId());
        User boris = userRepository.findById(borisId).orElseThrow();
        assertEquals(3, boris.getGroups().size());
    }
}
