package com.example.demo.user.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder 
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInputPassword {
	@NotBlank(message= "���� ��й�ȣ�� �ʼ� �׸��Դϴ�.")
	private String password;
	
	@Size(min=4, max=20, message="�ű� ��й�ȣ�� 4-20 ������ ���̷� �Է��� �ּ���")
	@NotBlank(message="�ű� ��й�ȣ�� �ʼ� �׸��Դϴ�")
	private String newPassword;
}
