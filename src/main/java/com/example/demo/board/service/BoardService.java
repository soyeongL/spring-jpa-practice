package com.example.demo.board.service;

import com.example.demo.board.entity.BoardType;
import com.example.demo.board.model.BoardTypeInput;
import com.example.demo.board.model.ServiceResult;

public interface BoardService {
	ServiceResult addBoard(BoardTypeInput boardTypeInput);
	ServiceResult updateBoard(BoardTypeInput boardTypeInput, Long id);
}
