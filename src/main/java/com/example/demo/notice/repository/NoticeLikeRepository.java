package com.example.demo.notice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.entity.NoticeLike;
import com.example.demo.user.entity.User;

@Repository
public interface NoticeLikeRepository  extends JpaRepository<NoticeLike, Long>{
	 List<NoticeLike> findByUser(User user);
}
