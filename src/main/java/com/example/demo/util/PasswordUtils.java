package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordUtils {
	//패스워드를 암호화해서 리턴하는 함수
	//입력한 패스워드를 해시된 패스워드와 빅
	
	public static boolean equalPassword(String password, String encPassowrd) {
		return BCrypt.checkpw(password, encPassowrd);
	}
}
