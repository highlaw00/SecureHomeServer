package com.securehome.config;

import com.securehome.entity.Member;
import com.securehome.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service // UserDetails를 반환하는 빈으로 등록. 아마 Spring Security가 UserDetailsService로 등록된 빈을 조회해서 인증을 진행하는 것 같다.
public class SecureHomeUserDetails implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        List<Member> member = memberRepository.findByName(username);
        if (member.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user: " + username);
        } else {
            userName = member.get(0).getName();
            password = member.get(0).getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(member.get(0).getRole()));
        }

        return new User(username, password, authorities);
    }
}
