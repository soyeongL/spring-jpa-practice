package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordUtils {
	//�н����带 ��ȣȭ�ؼ� �����ϴ� �Լ�
	//�Է��� �н����带 �ؽõ� �н������ ��
	
	public static boolean equalPassword(String password, String encPassowrd) {
		return BCrypt.checkpw(password, encPassowrd);
	}
}
