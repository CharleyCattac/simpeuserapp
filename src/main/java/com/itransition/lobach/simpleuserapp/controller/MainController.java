package com.itransition.lobach.simpleuserapp.controller;

import com.itransition.lobach.simpleuserapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.itransition.lobach.simpleuserapp.controller.ControllerConstants.*;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showIndex() {
        return URL_INDEX;
    }

    @GetMapping("/table_edit")
    public String showTable(Model model) {
        model.addAttribute(ATTRIBUTE_LIST, userService.findAll());
        return URL_TABLE_EDIT;
    }

    @GetMapping("/table_readonly")
    public String showAllowedTable(Model model) {
        model.addAttribute(ATTRIBUTE_MESSAGE, ATTRIBUTE_MSG_ANON);
        model.addAttribute(ATTRIBUTE_LIST, userService.findAll());
        return URL_TABLE_READONLY;
    }

    @GetMapping("/logout")
    public String logOut(Model model) {
        model.addAttribute(ATTRIBUTE_MESSAGE, ATTRIBUTE_MSG_LOGOUT);
        return URL_LOGIN;
    }

}