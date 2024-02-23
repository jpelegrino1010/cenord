package com.julioluis.noahrdsystem.services;

import com.julioluis.noahrdsystem.dtos.ResponseDTO;
import com.julioluis.noahrdsystem.model.Member;
import com.julioluis.noahrdsystem.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseDTO<Member> findMemberByEmail(String email) {
        ResponseDTO<Member> response = new ResponseDTO<>();
        try {
            Member member = memberRepository.findByEmail(email);
            response.setData(member);
            response.setSuccess(true);
            return response;
        }catch (Exception ex) {
            response.setMessage(ex.getMessage());
            return response;
        }

    }

    public ResponseDTO<Member> save(Member newMember) {
        ResponseDTO<Member> response = new ResponseDTO<>();
        try {
            String encodedPass = passwordEncoder.encode(newMember.getPassword());
            newMember.setPassword(encodedPass);
            newMember.setEnabled(true);
            Member memberSaved = memberRepository.save(newMember);
            response.setData(memberSaved);
            response.setSuccess(true);
            return response;
        } catch (Exception ex) {
            response.setMessage(ex.getMessage());
            return response;
        }

    }

    public ResponseDTO<List<Member>> findAll() {
        ResponseDTO<List<Member>> response = new ResponseDTO<>();
        try {
            List<Member> members = memberRepository.findAll();
            response.setData(members);
            response.setSuccess(true);
            return response;
        }catch (Exception ex) {
            response.setMessage(ex.getMessage());
            return response;
        }

    }
}
