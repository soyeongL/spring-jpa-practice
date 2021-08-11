package com.example.demo.board.exception;

public class BoardNotExistedException extends RuntimeException {
	public BoardNotExistedException(String message) {
		super(message);
	}
}
