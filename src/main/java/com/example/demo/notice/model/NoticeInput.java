package com.example.demo.notice.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeInput {
	//id , 제목, 내용, 등록일
	@Size(min=10, max = 100,message="제목은 10자-100자 사이의 값입니다.")
	@NotBlank(message = "제목은 필수 항목 입니다")
	private String title;
	
	@Size(min=50, max=100, message="내용은 50자-100자 사이의 값입니다.")
	@NotBlank(message="내용은 필수 항목 입니다.")
	private String contents;
}
