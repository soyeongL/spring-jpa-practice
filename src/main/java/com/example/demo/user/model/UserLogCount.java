package com.example.demo.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogCount {
	private long id;
	private String email;
	private String userName;
	private long NoticeCount;
	private long noticeLikedCount;
}
