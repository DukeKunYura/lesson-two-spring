package com.example.lessontwospring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.*;

@Entity
@Table(name = "user_groups")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserGroup {
    @Id
    @Column(name = "user_group_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @EqualsAndHashCode.Include
    UUID id;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL)
    List<User> users = new ArrayList<>();

    public void addUser(User user) {
        if(user.getId() == null || !users.contains(user)) {
            user.setUserGroup(this);
            users.add(user);
        }
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
