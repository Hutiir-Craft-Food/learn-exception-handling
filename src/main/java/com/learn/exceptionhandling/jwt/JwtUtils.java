package com.learn.exceptionhandling.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {
	
	private final Algorithm algorithm;

	@Value("${jwt.session-timeout}")
	private long sessionTimeout;
	
	public String generateJwtToken(String email) {
		return JWT.create()
				.withSubject(email)
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + sessionTimeout * 1000))
				.sign(algorithm);
	}
}

