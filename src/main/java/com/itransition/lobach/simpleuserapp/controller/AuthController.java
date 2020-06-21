package com.itransition.lobach.simpleuserapp.controller;

import com.itransition.lobach.simpleuserapp.domain.User;
import com.itransition.lobach.simpleuserapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static com.itransition.lobach.simpleuserapp.controller.ControllerConstants.*;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/signup")
    public String showSignUp() {
        return URL_SIGNUP;
    }

    @PostMapping("/signup")
    public String sighUp(@RequestParam String name,
                         @RequestParam String username,
                         @RequestParam String password,
                         Model model) {
        Optional<User> userFromDb = userService.findUserByUsername(username);

        if (userFromDb.isPresent()) {
            model.addAttribute(ATTRIBUTE_ERROR, ATTRIBUTE_ERR_SIGN_UP_USER_EXISTS);
            return URL_SIGNUP;
        }

        try {
            userService.saveUser(name, username, password);
        } catch (RuntimeException e) {
            model.addAttribute(ATTRIBUTE_ERROR, ATTRIBUTE_ERR_SIGN_UP);
            return URL_SIGNUP;
        }
        model.addAttribute(ATTRIBUTE_MESSAGE, ATTRIBUTE_MSG_SIGNUP_SUCCESSFUL);

        return URL_LOGIN_REDIRECT;
    }

    @PostMapping("/signin")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR, ATTRIBUTE_ERR_LOGIN_INVALID_DATA);
            return URL_LOGIN;
        }

        UserDetails userDetails = userService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password);
        authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(token);

        userService.updateLastCheckoutTime(username);

        if(token.isAuthenticated()) {
            model.addAttribute(ATTRIBUTE_MESSAGE, ATTRIBUTE_MSG_LOGIN_SUCCESSFUL);
            return URL_INDEX;
        } else {
            model.addAttribute(ATTRIBUTE_ERROR, ATTRIBUTE_ERR_LOGIN);
            model.addAttribute(ATTRIBUTE_MESSAGE, "Well yes, but actually no.");
            return URL_LOGIN;
        }
    }

}
