package com.julioluis.noahrdsystem.services;

import com.julioluis.noahrdsystem.dtos.ResponseDTO;
import com.julioluis.noahrdsystem.model.Authority;
import com.julioluis.noahrdsystem.model.Member;
import com.julioluis.noahrdsystem.model.Rol;
import com.julioluis.noahrdsystem.model.RolAuthority;
import com.julioluis.noahrdsystem.repositories.AuthorityRepository;
import com.julioluis.noahrdsystem.repositories.MemberRepository;
import com.julioluis.noahrdsystem.repositories.RolAuthorityRepository;
import com.julioluis.noahrdsystem.repositories.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolAuthorityRepository rolAuthorityRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

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

    public ResponseDTO<Member> findById(long id) {
        ResponseDTO<Member> response = new ResponseDTO<>();
        try {
            Member member = memberRepository.findById(id).orElse(null);
            response.setData(member);
            response.setSuccess(true);
            return response;
        }catch (Exception ex) {
            response.setMessage(ex.getMessage());
            return response;
        }

    }

    public ResponseDTO<Member> updateMember(long id, Member modifyMember) {
        ResponseDTO<Member> response = new ResponseDTO<>();
        try {
            ResponseDTO<Member> memberResponse = findById(id);
            if(memberResponse.isSuccess()) {
                Optional<Member> member = Optional.of(memberResponse.getData());
                member.map(member1 -> {
                        member1.setEnabled(Objects.isNull(modifyMember.getEnabled())
                                ?member1.getEnabled(): modifyMember.getEnabled());
                        member1.setRol(Objects.isNull(modifyMember.getRol())
                        ?member1.getRol() : modifyMember.getRol());
                        member1.setPassword(Objects.isNull(modifyMember.getPassword())
                        ?member1.getPassword() : modifyMember.getPassword());
                        member1.setFirstName(Objects.isNull(modifyMember.getFirstName())
                        ?member1.getFirstName() : modifyMember.getFirstName());
                        member1.setLastName(Objects.isNull(modifyMember.getLastName())
                        ?member1.getLastName() : modifyMember.getLastName());
                        member1.setImagePath(Objects.isNull(modifyMember.getImagePath())
                        ?member1.getImagePath() : modifyMember.getImagePath());
                        response.setData(member1);
                        return memberRepository.save(member1);
                        }
                        );

                response.setSuccess(true);

            }else {
                response.setMessage("Invalid member Id");
                           }
            return response;
        }catch (Exception ex) {
            response.setMessage(ex.getMessage());
            return response;
        }

    }

    public ResponseDTO<Member> assignAuthority(Member member) {
        ResponseDTO<Member> response = new ResponseDTO<>();

        try {
           Member memberUpdated = memberRepository.save(member);
            response.setData(memberUpdated);
            response.setSuccess(true);
            return response;
        }catch (Exception ex) {
            response.setMessage(ex.getMessage());
            return response;
        }


    }

    public Rol findRolById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Authority findAuthorityById(Long id) {
        return authorityRepository.findById(id).orElse(null);
    }

}
