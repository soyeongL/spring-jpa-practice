package com.example.demo.user.service;

import org.springframework.stereotype.Service;

import com.example.demo.user.model.UserStatus;
import com.example.demo.user.model.UserSummary;
import com.example.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

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
	
}
