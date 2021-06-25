package com.example.demo.user.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Notice;
import com.example.demo.entity.NoticeLike;
import com.example.demo.entity.User;
import com.example.demo.notice.model.NoticeResponse;
import com.example.demo.notice.model.ResponseError;
import com.example.demo.notice.repository.NoticeLikeRepository;
import com.example.demo.notice.repository.NoticeRepository;
import com.example.demo.user.exception.ExistsEmailException;
import com.example.demo.user.exception.PasswordNotMatchException;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.model.UserInput;
import com.example.demo.user.model.UserInputFind;
import com.example.demo.user.model.UserInputPassword;
import com.example.demo.user.model.UserResponse;
import com.example.demo.user.model.UserUpdate;
import com.example.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiUserController {
	private final UserRepository userRepository;
	private final NoticeRepository noticeRepository;
	private final NoticeLikeRepository noticeLikeRepository;
	
	/*@PostMapping("/api/user")
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
	*/
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
	
	private String getEncryptPassword(String password) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(password);
	}
	
	@PostMapping("/api/user")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach((e)->{
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		if(userRepository.countByEmail(userInput.getEmail())> 0) {
			throw new ExistsEmailException("이미 존재하는 이메일입니다");
		}
		
		String encryptPassword = getEncryptPassword(userInput.getPassword());
		
		
		User user = User.builder()
					.email(userInput.getEmail())
					.userName(userInput.getUserName())
					.phone(userInput.getPhone())
					.password(encryptPassword)
					.regDate(LocalDateTime.now())
					.build();
		
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	
	@ExceptionHandler(value = {ExistsEmailException.class, PasswordNotMatchException.class})
	public ResponseEntity<?> existsEmailExceptionHandler(RuntimeException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@PatchMapping("/api/user/{id}/password") 
	public ResponseEntity<?> updateUserPassword(@PathVariable long id, 
			@RequestBody @Valid UserInputPassword userInputPassword,
			Errors errors ) {
		
		
		List<ResponseError> responseErrorList = new ArrayList<>();
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach((e)->{
				responseErrorList.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
		}
		User user = userRepository.findByIdAndPassword(id, userInputPassword.getPassword())
						.orElseThrow(()->new PasswordNotMatchException("비밀번호가 일치하지 않습니다"));
		user.setPassword(userInputPassword.getNewPassword());
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/api/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable long id) {
		User user = userRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException("사용자 정보가 없습니다."));
		
		//내가 쓴 공지사항이 있는 경우
		// 1. 삭제할 수 없음 >> 공지사항 삭제하고 와라
		// 2. 회원 삭제 전에 공지사항 글을 다 삭제한다
	
		try {
			userRepository.delete(user);	
		}catch(DataIntegrityViolationException e) {
			String message = "제약조건에 문제가 발생하였습니다.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
					
		}catch(Exception e) {
			String message = "제약조건에 문제가 발생하였습니다.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/api/user")
	public  ResponseEntity<?> findUser(@RequestBody UserInputFind userInputFind) {
		User user = userRepository.findByUserNameAndPhone(userInputFind.getUserName(), userInputFind.getPhone())
			.orElseThrow(()-> new UserNotFoundException("찾는 사용자가 없습니다"));
		UserResponse userResponse = UserResponse.of(user);
		return ResponseEntity.ok().body(userResponse);
	}
	
	@GetMapping("/api/user/{id}/password/reset")
	public ResponseEntity<?> resetUserPassword(@PathVariable long id) {
		User user=  userRepository.findById(id)
			.orElseThrow(()-> new UserNotFoundException("사용자 정보가 없습니다"));
		//비밀번호 초기화
		String resetPassword = getResetPassword();
		String resetEncPassword = getEncryptPassword(resetPassword);
		user.setPassword(resetEncPassword);
		userRepository.save(user);
		String message = String.format("[%s]님의 임시 비밀번호가 [%s]로 초기화 되었습니다", user.getUserName(),resetPassword);
		sendSMS(message);
		
		return ResponseEntity.ok().build();
	}
	
	void sendSMS(String message) {
		System.out.println("[문자메시지전송]");
		System.out.println(message);
	}
	
	private String getResetPassword() {
		return UUID.randomUUID().toString().replace("-", "").substring(0,10);
	}
	
	@GetMapping("/api/user/{id}/notice/like")
	public List<NoticeLike> likeNotice(@PathVariable long id) {
		User user = userRepository.findById(id)
			.orElseThrow(()-> new UserNotFoundException("사용자가 없습니다"));
		
		List<NoticeLike> noticeLikeList = noticeLikeRepository.findByUser(user);
		return noticeLikeList;
	}
}
