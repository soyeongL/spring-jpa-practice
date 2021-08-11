package com.example.demo.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.notice.repository.NoticeRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.entity.UserLoginHistory;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.model.ResponseMessage;
import com.example.demo.user.model.UserLogCount;
import com.example.demo.user.model.UserNoticeCount;
import com.example.demo.user.model.UserResponse;
import com.example.demo.user.model.UserSearch;
import com.example.demo.user.model.UserStatus;
import com.example.demo.user.model.UserStatusInput;
import com.example.demo.user.model.UserSummary;
import com.example.demo.user.repository.UserLoginHistoryRepository;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserService;

import kotlin.io.path.ExperimentalPathApi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiAdminUserController {
	private final UserRepository userRepository;
	private final NoticeRepository noticeRepository;
	private final UserLoginHistoryRepository userLoginHistoryRepository;
	private final UserService userService;
	
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
	
	@GetMapping("/api/admin/user/search")
	public ResponseEntity<?> findUser(@RequestBody UserSearch userSearch) {
		//email like %|| email || %
		//email like concat //
		List<User> userList= 
		userRepository.findByEmailContainsAndPhoneContainsAndUserNameContains(
				userSearch.getEmail(), userSearch.getPhone(), userSearch.getUserName());
		return ResponseEntity.ok().body(ResponseMessage.success(userList));
	}
	
	@PatchMapping("/api/admin/user/{id}/status")
	public ResponseEntity<?> userStatus(@PathVariable Long id, @RequestBody UserStatusInput userStatusInput) {
		Optional<User> optionUser = userRepository.findById(id);
		if(!optionUser.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
		}
		User user = optionUser.get();
		user.setStatus(userStatusInput.getStatus());
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/api/admin/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		Optional<User> optionUser = userRepository.findById(id);
		if(!optionUser.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
		}
		User user = optionUser.get();
		if(noticeRepository.countByUser(user)>0) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자가 작성한 공지사항이 있습니다"), HttpStatus.BAD_REQUEST);
		}
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/api/admin/user/login/history")
	public ResponseEntity<?> userLoginHistory() {
		List<UserLoginHistory> list = userLoginHistoryRepository.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@PatchMapping("/api/admin/user/{id}/lock")
	public ResponseEntity<?> userLock(@PathVariable Long id) {
		Optional<User> optionUser = userRepository.findById(id);
		if(!optionUser.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
		}
		User user = optionUser.get();
		if(user.isLockYn()) {
			return new ResponseEntity<>(ResponseMessage.fail("이미 접속 제한된 사용자"),HttpStatus.BAD_REQUEST);
		}
		user.setLockYn(true);
		userRepository.save(user);
		return ResponseEntity.ok().body(ResponseMessage.success());
	}
	
	@PatchMapping("/api/admin/user/{id}/unlock")
	public ResponseEntity<?> userUnLock(@PathVariable Long id) {
		Optional<User> optionUser = userRepository.findById(id);
		if(!optionUser.isPresent()) {
			return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
		}
		User user = optionUser.get();
		if(!user.isLockYn()) {
			return new ResponseEntity<>(ResponseMessage.fail("이미 접속 제한이 해제된 사용자"),HttpStatus.BAD_REQUEST);
		}
		user.setLockYn(false);
		userRepository.save(user);
		return ResponseEntity.ok().body(ResponseMessage.success());
	}
	
	@GetMapping("/api/admin/user/status/count")
	public HttpEntity<?> userStatusCount() {
		//userRepository.countByStatus(UserStatus.Using);
		UserSummary userSummary = userService.getUserStatusCount();
		
		return ResponseEntity.ok().body(ResponseMessage.success(userSummary));
	}
	
	@GetMapping("/api/admin/user/today")
	public ResponseEntity<?> todayUser() {
		List<User> users = userService.getTodayUsers();
		
		return ResponseEntity.ok().body(ResponseMessage.success(users));
		  
	}
	@GetMapping("/api/admin/user/notice/count")
	public ResponseEntity<?> getNoticeCount(){
		List<UserNoticeCount> userNoticeCountList = userService.getUserNoticeCount();
		return ResponseEntity.ok().body(ResponseMessage.success(userNoticeCountList));
	}
	
	@GetMapping("/api/admin/user/log/count")
	public ResponseEntity<?> userLogCount() {
		List<UserLogCount> userLogCounts = userService.getUserLogCount();
		return ResponseEntity.ok().body(ResponseMessage.success(userLogCounts));
	}
	
	@GetMapping("/api/admin/user/likes/best")
	public ResponseEntity<?> userLikesBest() {
		List<User> user= userService.getLikesBestUserList();
		return ResponseEntity.ok().body(ResponseMessage.success(user));
	}
}
