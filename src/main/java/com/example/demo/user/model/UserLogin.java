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
	
	@NotBlank(message="email 은 필수입니다.")
	private String email;
	@NotBlank(message="password 는 필수입니다")
	private String password;
}
