package com.example.demo.notice.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseError {
	private String field;
	private String messgae;
	
	public static ResponseError of(FieldError e) {
		return ResponseError.builder()
			.field(e.getField())
			.messgae(e.getDefaultMessage())
			.build(); 
	}
	
	public static List<ResponseError> of(List<ObjectError> errors){
		List<ResponseError> responseErrors = new ArrayList<>();
		if(errors != null) {
			errors.stream().forEach((e)->{
				responseErrors.add(ResponseError.of((FieldError)e)); 
			});
		}
		return responseErrors;
	}
}
