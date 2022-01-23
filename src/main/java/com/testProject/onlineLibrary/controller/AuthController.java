package com.testProject.onlineLibrary.controller;

import com.testProject.onlineLibrary.controller.param.RegistrationState;
import com.testProject.onlineLibrary.domain.Role;
import com.testProject.onlineLibrary.domain.User;
import com.testProject.onlineLibrary.repo.UserRepo;
import com.testProject.onlineLibrary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AuthController {

    private final UserRepo userRepo;
    private final UserService userService;

    public static final String REGISTRATION_PAGE = "registration";

    public AuthController(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        RegistrationState registrationState = new RegistrationState();
        model.addAttribute("registrationState", registrationState);
        return REGISTRATION_PAGE;
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        boolean isNewUser = userService.addUser(user);
        RegistrationState registrationState = new RegistrationState();

        if(!isNewUser){
            //todo: Скорее всего неправильное использование
            registrationState.setRegistrationState(true);
            model.addAttribute("registrationState", registrationState);
            return REGISTRATION_PAGE;
        }

        return "redirect:/login";
    }
}
