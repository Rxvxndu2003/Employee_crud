package com.example.employee_crud.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/user")
    public OAuth2User getUser(Principal principal) {
        return (OAuth2User) ((Authentication) principal).getPrincipal();
    }
}