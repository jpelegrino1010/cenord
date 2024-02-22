package com.julioluis.noahrdsystem.controllers;

import com.julioluis.noahrdsystem.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/user")
    public String getUserDetailsAfterLogin(Authentication authentication) {

       if(authentication.isAuthenticated()) {
           return "successfully loged in";
       }

       return "Sorry";
    }
}
