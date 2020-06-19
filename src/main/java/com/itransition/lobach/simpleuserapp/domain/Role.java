package com.itransition.lobach.simpleuserapp.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
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

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users = new HashSet<>();
}
