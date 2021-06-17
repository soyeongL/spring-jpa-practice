package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Notice;

@Repository
public interface NoticeRepository  extends JpaRepository<Notice, Long>{
	
	Optional<List<Notice>> findByIdIn(List<Long> idList);
	
}
