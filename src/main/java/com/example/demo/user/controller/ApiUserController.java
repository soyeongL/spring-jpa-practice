package com.example.demo.user.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Notice;
import com.example.demo.entity.User;
import com.example.demo.notice.model.NoticeResponse;
import com.example.demo.notice.model.ResponseError;
import com.example.demo.notice.repository.NoticeRepository;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.model.UserInput;
import com.example.demo.user.model.UserResponse;
import com.example.demo.user.model.UserUpdate;
import com.example.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiUserController {
	private final UserRepository userRepository;
	private final NoticeRepository noticeRepository;
	
	@PostMapping("/api/user")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().forEach((e)->{
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		} 
		
		User user = User.builder()
				.email(userInput.getEmail())
				.userName(userInput.getUserName())
				.password(userInput.getPassword())
				.phone(userInput.getPhone())
				.regDate(LocalDateTime.now())
				.build();
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/api/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id ,@RequestBody @Valid UserUpdate userUpdate, Errors errors) {
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().forEach((e)->{
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		
		User user = userRepository.findById(id)
			.orElseThrow(()-> new UserNotFoundException("사용자 정보가 없습니다."));
	
		user.setPhone(userUpdate.getPhone());
		user.setUpdateDate(LocalDateTime.now());
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/api/user/{id}")
	public UserResponse getUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
						.orElseThrow(()-> new UserNotFoundException("사용자 정보가 없습니다."));
		UserResponse userResponse = UserResponse.of(user);
		
		return userResponse;
	}
	
	@GetMapping("/api/user/{id}/notice")
	public List<NoticeResponse> userNotice(@PathVariable Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(()-> new UserNotFoundException("사용자 정보가 없습니다."));
	
		List<Notice> noticeList = noticeRepository.findByUser(user);
		List<NoticeResponse> noticeResponseList = new ArrayList<>();
		noticeList.stream().forEach((e)->{
			noticeResponseList.add(NoticeResponse.of(e));
		});
		return noticeResponseList;
	}
}
