package com.securehome.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/authorized")
    public ResponseEntity<?> authorizedRequest() {
        return ResponseEntity.ok().body("Hello authorized user.");
    }

    @GetMapping("/unauthorized")
    public ResponseEntity<?> unauthorizedRequest() {
        return ResponseEntity.ok().body("Hello anonymous user.");
    }
}
