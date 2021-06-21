package com.example.demo.notice.model;

import java.time.LocalDateTime;

import com.example.demo.entity.Notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponse {
	private long id;
	private long regUserId;
	private String regUserName;
	private String title;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private int hits;
	private int likes;
		
	public static NoticeResponse of(Notice notice) {
		return NoticeResponse.builder()
					.id(notice.getId())
					.regUserId(notice.getUser().getId())
					.regUserName(notice.getUser().getUserName())
					.title(notice.getTitle())
					.regDate(notice.getRegDate())
					.updateDate(notice.getUpdateDate())
					.hits(notice.getHits())
					.likes(notice.getLikes())
					.build();
	}
}