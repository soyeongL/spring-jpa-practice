package com.example.demo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.board.entity.BoardType;

public interface BoardTypeRepository extends JpaRepository<BoardType, Long>{
	BoardType findByBoardName(String name);
}
