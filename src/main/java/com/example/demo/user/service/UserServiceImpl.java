package com.example.demo.user.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.user.model.UserLogCount;
import com.example.demo.user.model.UserNoticeCount;
import com.example.demo.user.model.UserStatus;
import com.example.demo.user.model.UserSummary;
import com.example.demo.user.repository.UserCustomRepository;
import com.example.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final UserCustomRepository userCustomRepository;

	@Override
	public UserSummary getUserStatusCount() {
		long usingUserCount = userRepository.countByStatus(UserStatus.Using);
		long stopUserCount = userRepository.countByStatus(UserStatus.Stop);
		long totalUserCount = userRepository.count();
		return UserSummary.builder()
				.usingUserCount(usingUserCount)
				.stopUserCount(stopUserCount)
				.totalUserCount(totalUserCount)
				.build();
	}

	@Override
	public List<User> getTodayUsers() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startDate = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(),0,0);
		LocalDateTime endDate = startDate.plusDays(1);
		return userRepository.findToday(startDate, endDate);
	}

	@Override
	public List<UserNoticeCount> getUserNoticeCount() {
		return userCustomRepository.findUserNoticeCount();
		
	}

	@Override
	public List<UserLogCount> getUserLogCount() {
		return userCustomRepository.findUserLogCount();
	}
	
}
