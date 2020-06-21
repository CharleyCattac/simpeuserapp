package com.itransition.lobach.simpleuserapp.builder;

import com.itransition.lobach.simpleuserapp.domain.Role;
import com.itransition.lobach.simpleuserapp.domain.User;

import java.util.Set;

public class UserBuilder {
    private UserBuilder() {
        super();
    }

    public static User buildUser(String name,
                                 String username,
                                 String password,
                                 Set<Role> roleSet,
                                 Long currentMillis) {
        return User.builder()
                .name(name)
                .username(username)
                .password(password)
                .authorities(roleSet)
                .millisWhenCreated(currentMillis)
                .millisWhenLastLogin(currentMillis)
                .blocked(false)
                .build();
    }
}
