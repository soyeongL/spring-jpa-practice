package com.example.demo.board.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardTypeInput {
	@NotBlank(message= "�Խ��� ������ �ʼ��׸��Դϴ�.")
	private String name;
	
}
