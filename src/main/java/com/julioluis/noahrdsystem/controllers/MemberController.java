package com.julioluis.noahrdsystem.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.julioluis.noahrdsystem.dtos.ResponseDTO;
import com.julioluis.noahrdsystem.model.Member;
import com.julioluis.noahrdsystem.model.RolAuthority;
import com.julioluis.noahrdsystem.services.MemberService;
import com.julioluis.noahrdsystem.utils.MapFilter;
import com.julioluis.noahrdsystem.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    //    @PreAuthorize("hasAuthority('MEMBER')")
    public MappingJacksonValue getAll() {
        ResponseDTO<List<Member>> response = memberService.findAll();

        FilterProvider filters = MapFilter.getFilterProvider("password");
        if(response.isSuccess()) {
            MappingJacksonValue mapping= new MappingJacksonValue(response.getData());
            mapping.setFilters(filters);
            return mapping;
        }

        throw new UserException(response.getMessage());
    }

    @GetMapping("/{id}")
    public MappingJacksonValue findById(@PathVariable Long id) {
        ResponseDTO<Member> response = memberService.findById(id);
        FilterProvider filters = MapFilter.getFilterProvider("password");

        if(response.isSuccess()) {
            MappingJacksonValue mapping= new MappingJacksonValue(response.getData());
            mapping.setFilters(filters);
            return mapping;
        }

        throw new UserException(response.getMessage());
    }



    @PostMapping
//    @PreAuthorize("hasAuthority('MEMBER')")
    public MappingJacksonValue create(@RequestBody Member member) {
        ResponseDTO<Member> response = memberService.save(member);

        FilterProvider filters = MapFilter.getFilterProvider("password");
        if(response.isSuccess()) {
            MappingJacksonValue mapping= new MappingJacksonValue(response.getData());
            mapping.setFilters(filters);
            return mapping;
        }

        throw new UserException(response.getMessage());
    }

    @PutMapping("/{id}")
    public MappingJacksonValue update(@PathVariable Long id, @RequestBody Member member) {
        ResponseDTO<Member> response = memberService.updateMember(id,member);

        FilterProvider filters = MapFilter.getFilterProvider("password");
        if(response.isSuccess()) {
            MappingJacksonValue mapping= new MappingJacksonValue(response.getData());
            mapping.setFilters(filters);
            return mapping;
        }

        throw new UserException(response.getMessage());
    }

    @PostMapping("/assign")
    public ResponseEntity<ResponseDTO> assignToAuthority(@RequestBody List<RolAuthority> authorities) {
        ResponseDTO<List<RolAuthority>> response = memberService.assignAuthority(authorities);

        if (response.isSuccess()) {
            return ResponseEntity.ok().body(response);
        }

        throw new UserException(response.getMessage());
    }

}
