package com.julioluis.noahrdsystem.services;

import com.julioluis.noahrdsystem.dtos.ResponseDTO;
import com.julioluis.noahrdsystem.model.Authority;
import com.julioluis.noahrdsystem.model.Member;
import com.julioluis.noahrdsystem.model.Rol;
import com.julioluis.noahrdsystem.model.RolAuthority;
import com.julioluis.noahrdsystem.repositories.MemberRepository;
import com.julioluis.noahrdsystem.repositories.RolAuthorityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {

    public static final long ID = 1L;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RolAuthorityRepository rolAuthorityRepository;
    @InjectMocks
    private MemberService service;
    private Member member;
    private List<Member> members;




    @Before
    public void setUp() {
        member=new Member();
        member.setId(1l);
        member.setEmail("jmartinez@gmail.com");

        Rol rol=new Rol();
        rol.setId(1l);

        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setId(1l);
        authorities.add(authority);
        rol.setAuthorities(authorities);

        member.setRol(rol);
        members = new ArrayList<>();
        members.add(member);
    }

    @Test
    public void test_find_member_by_email() {
        String email="email";
        when(memberRepository.findByEmail(anyString())).thenReturn(member);

        ResponseDTO<Member> response = service.findMemberByEmail(email);

        assertTrue(response.isSuccess());

        verify(memberRepository,times(1)).findByEmail(anyString());
    }

    @Test
    public void test_save_member() {
        Member newMember = new Member();
        newMember.setEmail("email");

        when(memberRepository.save(any(Member.class))).thenReturn(member);

        ResponseDTO<Member> response = service.save(newMember);
        assertTrue(response.isSuccess());

        verify(memberRepository,times(1)).save(any(Member.class));

    }

    @Test
    public void test_find_all_users() {
        when(memberRepository.findAll()).thenReturn(members);
        ResponseDTO<List<Member>> response = service.findAll();

        assertTrue(response.isSuccess());
        verify(memberRepository,times(1)).findAll();
    }

    @Test
    public void test_find_by_id() {
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
        ResponseDTO<Member> response = service.findById(1L);

        assertTrue(response.isSuccess());
        verify(memberRepository,times(1)).findById(anyLong());

    }

    @Test
    public void test_update_member() {

        Member modifyMember = new Member();
        modifyMember.setId(1L);
        modifyMember.setEnabled(false);

        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        ResponseDTO<Member> response = service.updateMember(ID,modifyMember);
        assertTrue(response.isSuccess());

        verify(memberRepository,times(1)).findById(anyLong());
        verify(memberRepository,times(1)).save(any(Member.class));

    }

//    @Test
//    public void test_save_rol_authority() {
//        Rol rol =new Rol();
//        rol.setId(1L);
//
//        Authority authority = new Authority();
//        authority.setId(1L);
//        List<RolAuthority> rolAuthorities = Arrays.asList(new RolAuthority(1l,rol,authority));
//
//        when(rolAuthorityRepository.saveAll(anyCollection())).thenReturn(rolAuthorities);
//        ResponseDTO<List<RolAuthority>> response = service.assignAuthority(rolAuthorities);
//
//        assertTrue(response.isSuccess());
//
//        verify(rolAuthorityRepository,times(1)).saveAll(anyCollection());
//    }





}
