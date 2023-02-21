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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    private UUID userGroupId;

    @Test
    @Rollback(value = false)
    void contextLoads() {
        assertNotNull(userRepository);
//        User user = new User();
//        user.setFirstName("Stan");
//        user.setSurname("Brown");
//        user.setAge(45);
//        user.setPassportNumber("676767");
//        userRepository.save(user);

    }
//    @Test
//    @Rollback(value = false)
    @PostConstruct
    void addUserGroup() {
        UserGroup userGroup = new UserGroup();
        userGroup.setName("Professors");
        userGroupRepository.save(userGroup);

        {
            User user = new User();
            user.setFirstName("Stan");
            user.setSurname("Brown");
            user.setAge(45);
            user.setPassportNumber("676767");
            userRepository.save(user);
            userGroup.getUsers().add(user);
        }
        {
            User user = new User();
            user.setFirstName("Stanly");
            user.setSurname("Browne");
            user.setAge(40);
            user.setPassportNumber("670000");
            userRepository.save(user);
            userGroup.getUsers().add(user);
        }
        userGroupRepository.save(userGroup);
        userGroupId = userGroup.getId();
    }


    @Test
    void showSelect() {
        UserGroup userGroup = userGroupRepository.findById(userGroupId).orElseThrow();
        List<User> userList = userGroup.getUsers();
        userList.size();
    }

//    @Test
//    @Rollback(value = false)
//    void addUserGroupInOneSetting() {
//        UserGroup userGroup = new UserGroup();
//        userGroup.setName("Professors");
//
//        {
//            User user = new User();
//            user.setFirstName("Stan");
//            user.setSurname("Brown");
//            user.setAge(45);
//            user.setPassportNumber("676767");
//            userGroup.getUsers().add(user);
//        }
//        {
//            User user = new User();
//            user.setFirstName("Stanly");
//            user.setSurname("Browne");
//            user.setAge(40);
//            user.setPassportNumber("670000");
//            userGroup.getUsers().add(user);
//        }
//        userGroupRepository.save(userGroup);
//    }
}
