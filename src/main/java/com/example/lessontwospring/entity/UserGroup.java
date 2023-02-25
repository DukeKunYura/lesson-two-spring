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
    @OneToMany
    @JoinTable(name = "users_user_groups"
            , joinColumns = {@JoinColumn(name = "user_group_id")}
            , inverseJoinColumns = {@JoinColumn(name = "user_id")})
    List<User> users = new ArrayList<>();

}
