package com.example.demo.user.model;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.demo.entity.User;

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
	private Object data;
	
	public static ResponseMessage fail(String message) {
		
		return ResponseMessage.builder()
			.header(ResponseMessageHeader.builder()
					.result(false)
					.resultCode("")
					.message(message)
					.status(HttpStatus.BAD_REQUEST.value())
					.build())
			.data(null)
			.build();
	}
	
	public static ResponseMessage success(Object data) {
		return ResponseMessage.builder()
				.header(ResponseMessageHeader.builder()
						.result(true)
						.resultCode("")
						.message("")
						.status(HttpStatus.OK.value())
						.build())
				.data(data)
				.build();
	}
	
}
