package com.securehome.config;

import com.securehome.entity.Member;
import com.securehome.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Component
public class SecureHomeUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /*
     * Todo: 데이터베이스에서 UserDetails를 추출하고, 인증을 거친뒤 인증 결과를 담은 authentication을 반환
     *  1. 데이터베이스에서 UserDetails 가져오기
     *  2. 비밀번호가 일치하는지 확인하기
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Member> members = memberRepository.findByName(username);
        if (!members.isEmpty()) {
            Member findMember = members.get(0);
            if (passwordEncoder.matches(password, findMember.getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(findMember.getRole().toString()));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid password.");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
