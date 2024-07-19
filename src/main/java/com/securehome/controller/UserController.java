package com.securehome.controller;


import com.securehome.entity.Member;
import com.securehome.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final MemberRepository memberRepository;

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers() {
        List<Member> members = memberRepository.findAll();
        return ResponseEntity.ok()
                .body(members);
    }
}
