package com.manhjava.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Roles")
public class Role {

    @Id
    String name;
    String description;

    @ManyToMany
    Set<Permission> permissions;
}
