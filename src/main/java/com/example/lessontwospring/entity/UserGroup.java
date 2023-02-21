package com.example.lessontwospring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @JoinColumn(name = "user_group_id")
    @OneToMany
    @LazyCollection(
            LazyCollectionOption.EXTRA
    )
    List<User> users = new ArrayList<>();
}
