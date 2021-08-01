package com.example.demo.user.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.demo.user.model.UserLogCount;
import com.example.demo.user.model.UserNoticeCount;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserCustomRepository {
	private final EntityManager entityManager;
	
	public List<UserNoticeCount> findUserNoticeCount(){
		String sql = " select u.id, u.email, u.user_name, ( select count(*) from Notice n where n.user_id = u.id ) from User u ";
		List<UserNoticeCount> list = entityManager.createNativeQuery(sql).getResultList();
		return list;
	}
	
	public List<UserLogCount> findUserLogCount(){
		String sql =" select u.id, u.email, u.user_name"+
					" , (select count(*) from Notice n where n.user_id= u.id ) notice_count" +
					" , (select count(*) from Notice_Like nl where nl.user_id= u.id) notice_like_count " +
					" from User u ";
		List<UserLogCount> list = entityManager.createNativeQuery(sql).getResultList();
		return list;
	}
}
