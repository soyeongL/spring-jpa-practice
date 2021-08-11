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
			//����Ÿ����
			return ServiceResult.fail("�̹� ������ �Խ��� ������ �����մϴ�.");
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
			return ServiceResult.fail("������ �̸��� ������ �Խ����Դϴ�.");
		}
		BoardType realBoardType = boardTypeRepository.findById(id)
				.orElseThrow(()-> new BoardNotExistedException("�������� �ʴ� �Խù��Դϴ�."));
		
		realBoardType.setBoardName(boardTypeInput.getName());
		realBoardType.setUpdateDate(LocalDateTime.now());
		boardTypeRepository.save(boardType);
		return ServiceResult.success("���� ����");
	}
	
	

}
