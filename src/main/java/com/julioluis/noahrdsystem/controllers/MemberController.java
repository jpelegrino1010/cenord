package com.julioluis.noahrdsystem.controllers;

import com.julioluis.noahrdsystem.dtos.ResponseDTO;
import com.julioluis.noahrdsystem.model.Member;
import com.julioluis.noahrdsystem.services.MemberService;
import com.julioluis.noahrdsystem.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    //    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<ResponseDTO> getAll() {
        ResponseDTO<List<Member>> response = memberService.findAll();
        if(response.isSuccess()) {
            return ResponseEntity.ok().body(response);
        }

        throw new UserException(response.getMessage());
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<ResponseDTO> create(@RequestBody Member member) {

        ResponseDTO<Member> response = memberService.save(member);
        if(response.isSuccess()) {
            return ResponseEntity.status(201).body(response);
        }

        throw new UserException(response.getMessage());
    }

}
