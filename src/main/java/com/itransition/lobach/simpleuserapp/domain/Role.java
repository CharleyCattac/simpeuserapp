package com.itransition.lobach.simpleuserapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String role;

    public Role() {}

    public Role(String role) {
        this.role = role;
    }

    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();
}
