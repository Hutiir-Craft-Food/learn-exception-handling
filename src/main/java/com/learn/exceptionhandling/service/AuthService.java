package com.learn.exceptionhandling.service;

import com.learn.exceptionhandling.exception.AnnotatedBlahException;
import com.learn.exceptionhandling.jwt.JwtUtils;
import com.learn.exceptionhandling.model.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public Object auth (AuthRequest authRequest) {
        String username = authRequest.username();
        String password = authRequest.password();
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(authToken);

        if (authentication.isAuthenticated()) {
            return Map.of("access-token", jwtUtils.generateJwtToken(username));
        }

        throw new AnnotatedBlahException("back off!");
    }
}
