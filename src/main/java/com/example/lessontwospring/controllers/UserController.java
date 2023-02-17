package com.example.lessontwospring.controllers;

import com.example.lessontwospring.entity.User;
import com.example.lessontwospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@RestController("/")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    @GetMapping(value = "user")
    public User getUser(@RequestParam(name = "id") UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @GetMapping(value = "user_by_passport")
    public List<User> getUserByPassportGr(@RequestParam(name = "passport") String passport) {
        return userRepository.findByPassportNumber(passport);
    }

    @GetMapping(value = "user_by_name")
    public List<User> findByFirstNameIs(@RequestParam(name = "firstName") String firstName) {
        return userRepository.findByFirstNameIs(firstName);
    }

    @GetMapping(value = "users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "add_user")
    public User addUser(@RequestParam(name = "passport", required = false) String passportNumber,
                        @RequestParam(name = "name", required = false) String name) {
        User user = new User();
        user.setFirstName(name);
        user.setPassportNumber(passportNumber);
        userRepository.save(user);

        return user;
    }
}
