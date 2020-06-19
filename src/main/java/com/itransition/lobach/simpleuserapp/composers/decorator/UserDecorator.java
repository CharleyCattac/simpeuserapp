package com.itransition.lobach.simpleuserapp.composers.decorator;

import com.itransition.lobach.simpleuserapp.domain.Role;
import com.itransition.lobach.simpleuserapp.domain.User;

import java.util.Collections;

public class UserDecorator {
    private UserDecorator() {
        super();
    }

    public static void decorate(User user) {
        long currentMillis = System.currentTimeMillis();
        user.setBlocked(false);
        user.setMillisWhenCreated(currentMillis);
        user.setMillisWhenLastLogin(currentMillis);
        user.setAuthorities(Collections.singleton(new Role("USER")));
    }
}
