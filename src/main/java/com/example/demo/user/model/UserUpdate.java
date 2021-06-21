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
	
	@Size(max=20, message="����ó�� �ִ� 20�ڱ��� �Է��ؾ��մϴ�")
	@NotBlank(message="����ó�� �ʼ� �׸� �Դϴ�.")
	private String phone;
}
