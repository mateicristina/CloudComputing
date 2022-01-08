package com.cloud.bgmeetup.services.controller;

import com.cloud.bgmeetup.services.dto.UserDto;
import com.cloud.bgmeetup.services.dto.UserLoginDto;
import com.cloud.bgmeetup.services.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginDto> register(@Valid @RequestBody UserLoginDto request) {
        authService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@Valid @RequestBody UserLoginDto request) {
        return authService.login(request);
    }
}
