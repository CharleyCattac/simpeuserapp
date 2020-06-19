package com.itransition.lobach.simpleuserapp.composers.builder;

import com.itransition.lobach.simpleuserapp.domain.Role;
import com.itransition.lobach.simpleuserapp.domain.User;

import java.util.Collections;

public class UserBuilder {
    private UserBuilder() {
        super();
    }

    public static User buildUser(String name,
                                 String username,
                                 String password,
                                 Long currentMillis) {
        return User.builder()
                .name(name)
                .username(username)
                .password(password)
                .authorities(Collections.singleton(new Role("USER")))
                .millisWhenCreated(currentMillis)
                .millisWhenLastLogin(currentMillis)
                .blocked(false)
                /*.accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .enabled(true)*/
                .build();

    }
}
