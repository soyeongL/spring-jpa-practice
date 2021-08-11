package com.example.demo.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.board.entity.BoardType;
import com.example.demo.board.model.BoardTypeInput;
import com.example.demo.board.model.ServiceResult;
import com.example.demo.board.service.BoardService;
import com.example.demo.notice.model.ResponseError;
import com.example.demo.user.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiBoardController {
	
	private final BoardService boardService;
	
	@PostMapping("/api/board/type")
	public ResponseEntity<?> addBoardType(@RequestBody @Valid BoardTypeInput boardTypeInput, Errors error) {
		if(error.hasErrors()) {
			List<ResponseError> responseErrors = ResponseError.of(error.getAllErrors());
			return new ResponseEntity<>(ResponseMessage.fail("입력 값이 정확하지 않습니다.", responseErrors), HttpStatus.BAD_REQUEST);
		}
		ServiceResult result = boardService.addBoard(boardTypeInput);
		if(!result.isResult()) {
			return ResponseEntity.ok(ResponseMessage.fail(result.getMessage()));
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/api/board/type/{id}")
	public ResponseEntity<?> updateBoardType(@PathVariable Long id,@RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {
		if(errors.hasErrors()) {
			List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());
			return new ResponseEntity<>(ResponseMessage.fail("입력 값이 정확하지 않습니다.", responseErrors), HttpStatus.BAD_REQUEST);
		}
		ServiceResult result = boardService.updateBoard(boardTypeInput, id);
		if(result.isResult()) {
			return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
		}
		
		return ResponseEntity.ok().build();
	}
}
