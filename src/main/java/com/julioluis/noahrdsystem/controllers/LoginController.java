package com.julioluis.noahrdsystem.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.julioluis.noahrdsystem.dtos.ResponseDTO;
import com.julioluis.noahrdsystem.model.Member;
import com.julioluis.noahrdsystem.repositories.MemberRepository;
import com.julioluis.noahrdsystem.services.MemberService;
import com.julioluis.noahrdsystem.utils.MapFilter;
import com.julioluis.noahrdsystem.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/user")
    public MappingJacksonValue getUserDetailsAfterLogin(Authentication authentication) {
        ResponseDTO<Member> response = memberService.findMemberByEmail(authentication.getName());
        FilterProvider filters = MapFilter.getFilterProvider("password");

        if(response.isSuccess()) {
            MappingJacksonValue mapping= new MappingJacksonValue(response.getData());
            mapping.setFilters(filters);
            return mapping;
        }

        throw new UserException(response.getMessage());
    }
}
