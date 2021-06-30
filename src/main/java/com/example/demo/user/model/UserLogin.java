package com.example.demo.user.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
	
	@NotBlank(message="email �� �ʼ��Դϴ�.")
	private String email;
	@NotBlank(message="password �� �ʼ��Դϴ�")
	private String password;
}
