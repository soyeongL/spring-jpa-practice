package com.example.demo.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResult {
	private boolean result;
	private String message;
	
	public static ServiceResult fail(String message) {
		return ServiceResult.builder()
				.result(false)
				.message(message)
				.build();
	}
	public static ServiceResult success(String message) {
		return ServiceResult.builder()
				.result(true)
				.message(message)
				.build();
	}
}
