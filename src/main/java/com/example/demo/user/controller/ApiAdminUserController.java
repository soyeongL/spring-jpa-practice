package com.example.demo.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.user.model.ResponseMessage;
import com.example.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiAdminUserController {
	private final UserRepository userRepository;
	
	@GetMapping("/api/admin/user")
	public ResponseMessage userList() {
		List<User> userList = userRepository.findAll();
		long totalUserCount = userRepository.count();
		
		return ResponseMessage.builder()
			//.totalCount(totalUserCount)
			//.data(userList)
			.build();
	}
	
	@GetMapping("/api/admin/user/{id}")
	public ResponseEntity<?> userDetail(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(ResponseMessage.success(user));
		
	}
}
