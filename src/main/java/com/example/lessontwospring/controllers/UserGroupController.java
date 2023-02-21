package com.example.lessontwospring.controllers;

import com.example.lessontwospring.entity.UserGroup;
import com.example.lessontwospring.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/group/")
@RequiredArgsConstructor
public class UserGroupController {
    private final UserGroupRepository userGroupRepository;
    @GetMapping(value = "group")
    public UserGroup getGroup(@RequestParam(name = "id") UUID id) {
        return userGroupRepository.findById(id).orElseThrow();
    }
    @GetMapping(value = "groups")
    public List<UserGroup> getAllUsers() {
        return userGroupRepository.findAll();
    }
    @GetMapping(value = "add_group")
    public UserGroup addGroup(@RequestParam(name = "name") String name) {
        UserGroup userGroup = new UserGroup();
        userGroup.setName(name);
        userGroupRepository.save(userGroup);
        return userGroup;
    }
}
