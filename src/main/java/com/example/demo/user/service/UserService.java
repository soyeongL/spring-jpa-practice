package com.example.demo.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.user.model.UserLogCount;
import com.example.demo.user.model.UserNoticeCount;
import com.example.demo.user.model.UserSummary;

public interface UserService {
	UserSummary getUserStatusCount();
	List<User> getTodayUsers();
	List<UserNoticeCount> getUserNoticeCount();
	List<UserLogCount> getUserLogCount();
}
