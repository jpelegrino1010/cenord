package com.julioluis.noahrdsystem.config;

import com.julioluis.noahrdsystem.model.Authority;
import com.julioluis.noahrdsystem.model.Member;
import com.julioluis.noahrdsystem.model.Rol;
import com.julioluis.noahrdsystem.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String pass=authentication.getCredentials().toString();

        Member member = memberRepository.findByEmail(username);
        if(Objects.nonNull(member)) {
            if(passwordEncoder.matches(pass,member.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username,pass,getGrantedAuthority(member.getRol()));
            }else {
                throw new BadCredentialsException("Invalid Password");
            }
        }else {
            throw new BadCredentialsException("No user register with this credentials");
        }

    }

    private List<GrantedAuthority> getGrantedAuthority(Rol rol) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Authority authority : rol.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
