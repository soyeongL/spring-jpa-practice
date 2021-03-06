package com.example.demo.notice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.notice.entity.Notice;
import com.example.demo.user.entity.User;

@Repository
public interface NoticeRepository  extends JpaRepository<Notice, Long>{
	
	Optional<List<Notice>> findByIdIn(List<Long> idList);
	Optional<List<Notice>> findByTitleAndContentsAndRegDateIsGreaterThanEqual(String title, String contents, LocalDateTime regDate);
	int countByTitleAndContentsAndRegDateIsGreaterThanEqual(String title, String contents, LocalDateTime regDate);
	
	List<Notice> findByUser(User user);
	
	long countByUser(User user);
}
