package com.example.demo.notice.exception;

public class DuplicatedNoticeException extends RuntimeException {
	public DuplicatedNoticeException(String message) {
		super(message);
	}
}
