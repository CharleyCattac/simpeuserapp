package com.itransition.lobach.simpleuserapp.domain;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String username;
    private String password;


    private Long millisWhenCreated;
    private Long millisWhenLastLogin;

    //private boolean accountNonLocked;
    private boolean blocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();

}
