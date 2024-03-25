package com.julioluis.noahrdsystem.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.julioluis.noahrdsystem.dtos.ResponseDTO;
import com.julioluis.noahrdsystem.model.Authority;
import com.julioluis.noahrdsystem.model.Member;
import com.julioluis.noahrdsystem.model.Rol;
import com.julioluis.noahrdsystem.model.RolAuthority;
import com.julioluis.noahrdsystem.services.MemberService;
import com.julioluis.noahrdsystem.utils.MapFilter;
import com.julioluis.noahrdsystem.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @PutMapping("/assign/{rolId}")
    public ResponseEntity<Void> assignToAuthority(Authentication authentication,
                                                         @PathVariable(value = "rolId") Long rolId,
                                                         @RequestBody List<Authority> authorities) {
        ResponseDTO response = null;

        Rol rol = memberService.findRolById(rolId);

        if (Objects.nonNull(rol)) {
            Set<Authority> authoritySet = rol.getAuthorities();

            for (Authority auth : authorities) {
                authoritySet.add(memberService.findAuthorityById(auth.getId()));
            }

            rol.setAuthorities(authoritySet);
            Member member = memberService.findMemberByEmail(authentication.getName()).getData();
            member.setRol(rol);

            memberService.assignAuthority(member);
            return ResponseEntity.ok().build();
        }

        throw new UserException(Objects.isNull(response)? "Rol no found": response.getMessage());
    }

}
