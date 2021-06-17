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
	//id , ����, ����, �����
	@Size(min=10, max = 100,message="������ 10��-100�� ������ ���Դϴ�.")
	@NotBlank(message = "������ �ʼ� �׸� �Դϴ�")
	private String title;
	
	@Size(min=50, max=100, message="������ 50��-100�� ������ ���Դϴ�.")
	@NotBlank(message="������ �ʼ� �׸� �Դϴ�.")
	private String contents;
}
