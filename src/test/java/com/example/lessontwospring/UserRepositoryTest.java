package com.example.lessontwospring;

import com.example.lessontwospring.entity.User;
import com.example.lessontwospring.entity.UserGroup;
import com.example.lessontwospring.repository.UserGroupRepository;
import com.example.lessontwospring.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    private UUID groupId;

    @PostConstruct
    void addUserGroup() {
        UserGroup userGroup = new UserGroup();
        userGroup.setName("Professors");
        userGroup = userGroupRepository.save(userGroup);
        groupId = userGroup.getId();

        {
            User user = new User();
            user.setFirstName("Stan");
            user.setSurname("Brown");
            user.setAge(45);
            user.setPassportNumber("676767");
            userGroup.addUser(user);
        }
        {
            User user = new User();
            user.setFirstName("Stanly");
            user.setSurname("Browne");
            user.setAge(40);
            user.setPassportNumber("670000");
            userGroup.addUser(user);
        }
        userGroupRepository.save(userGroup);
    }

    @Test
    void showSelect() {
        UserGroup userGroup = userGroupRepository.findById(groupId).orElseThrow();
        List<User> userList = userGroup.getUsers();
        userList.size();
    }

    @Test
    @Rollback(value = false)
    void addUserGroupInOneSetting() {
        UserGroup userGroup = userGroupRepository.findById(groupId).orElseThrow();
        userGroup.getUsers().size();
        System.out.println("userGroup.getUsers().size() = " + userGroup.getUsers().size());
        assertEquals(2, userGroup.getUsers().size());
    }
}
