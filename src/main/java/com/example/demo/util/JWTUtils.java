package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JWTUtils {
	private static final String KEY = "soyeonglee";
	
	public static String getIssure(String token) {
		String issuer = JWT.require(Algorithm.HMAC512(KEY.getBytes()))
				.build()
				.verify(token)
				.getIssuer();
		return issuer;
	}
}
