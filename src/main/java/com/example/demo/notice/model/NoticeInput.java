package com.example.demo.notice.model;

import java.time.LocalDateTime;

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
	private String title;
	private String contents;
}
