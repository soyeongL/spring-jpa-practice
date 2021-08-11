package com.example.demo.user.model;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.demo.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
	
	private ResponseMessageHeader header;
	private Object body;
	
	public static ResponseMessage fail(String message) {
		return fail(message, null);
	}
	
	public static ResponseMessage fail(String message, Object data) {
		return ResponseMessage.builder()
			.header(ResponseMessageHeader.builder()
					.result(false)
					.resultCode("")
					.message(message)
					.status(HttpStatus.BAD_REQUEST.value())
					.build())
			.body(data)
			.build();
	}
	
	public static ResponseMessage success(Object body) {
		return ResponseMessage.builder()
				.header(ResponseMessageHeader.builder()
						.result(true)
						.resultCode("")
						.message("")
						.status(HttpStatus.OK.value())
						.build())
				.body(body)
				.build();
	}
	public static ResponseMessage success() {
		return success(null);
	}
}
