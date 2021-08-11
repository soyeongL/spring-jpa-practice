package com.example.demo.board.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.board.entity.BoardType;
import com.example.demo.board.exception.BoardNotExistedException;
import com.example.demo.board.model.BoardTypeInput;
import com.example.demo.board.model.ServiceResult;
import com.example.demo.board.repository.BoardTypeRepository;

import kotlin.Result;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
	private final BoardTypeRepository boardTypeRepository;
	@Override
	public ServiceResult addBoard(BoardTypeInput boardTypeInput) {
		
		BoardType boardType =boardTypeRepository.findByBoardName(boardTypeInput.getName());
		if(boardType != null && boardTypeInput.getName().equals(boardType.getBoardName())) {
			//동일타입의
			return ServiceResult.fail("이미 동일한 게시판 제목이 존재합니다.");
		}
		BoardType addBoardType = BoardType.builder()
									.boardName(boardTypeInput.getName())
									.regDate(LocalDateTime.now())
									.build();
		boardTypeRepository.save(addBoardType);
		return ServiceResult.success("");
	}
	
	@Override
	public ServiceResult updateBoard(BoardTypeInput boardTypeInput, Long id) {
		BoardType boardType = boardTypeRepository.findByBoardName(boardTypeInput.getName());
		if(boardType != null) {
			return ServiceResult.fail("수정할 이름이 동일한 게시판입니다.");
		}
		BoardType realBoardType = boardTypeRepository.findById(id)
				.orElseThrow(()-> new BoardNotExistedException("존재하지 않는 게시물입니다."));
		
		realBoardType.setBoardName(boardTypeInput.getName());
		realBoardType.setUpdateDate(LocalDateTime.now());
		boardTypeRepository.save(boardType);
		return ServiceResult.success("수정 성공");
	}
	
	

}
