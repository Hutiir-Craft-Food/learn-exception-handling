package com.learn.exceptionhandling.controller;

import com.learn.exceptionhandling.model.AuthRequest;
import com.learn.exceptionhandling.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public Object auth (@Validated @RequestBody AuthRequest authRequest) {
        return authService.auth(authRequest);
    }
}
