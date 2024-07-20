package com.securehome.controller;

import com.securehome.entity.Member;
import com.securehome.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody Member member) {
        Member savedMember = null;
        ResponseEntity response = null;
        try {
            String givenPassword = member.getPassword();
            String encodedPassword = passwordEncoder.encode(givenPassword);
            member.setPassword(encodedPassword);
            savedMember = memberRepository.save(member);
            if (savedMember.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registerd");
            }
        } catch (Exception exception) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + exception.getMessage());
        }

        return response;
    }
}
