package com.example.demo.user.model;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdate {
	
	@Size(max=20, message="연락처는 최대 20자까지 입력해야합니다")
	@NotBlank(message="연락처는 필수 항목 입니다.")
	private String phone;
}
