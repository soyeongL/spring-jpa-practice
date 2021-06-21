package com.example.demo.user.model;

import com.example.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResponse {
	private Long id;
	private String email;
	private String userName;
	private String phone;
	
	public static UserResponse of(User user) {
		return UserResponse.builder()
					.id(user.getId())
					.userName(user.getUserName())
					.phone(user.getPhone())
					.email(user.getEmail())
					.build();
	}
}
