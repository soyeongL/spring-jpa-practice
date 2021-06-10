package com.example.demo.notice.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Notice;
import com.example.demo.notice.model.NoticeInput;
import com.example.demo.notice.model.NoticeModel;
import com.example.demo.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiNoticeController {
	
	private final NoticeRepository noticeRepository;
	/*
	@GetMapping("/api/notice")
	public String notcieString() {
		return "공지사항입니다.";
	}
	*/
	
	/*
	@GetMapping("/api/notice")
	public NoticeModel getNotice() {
		LocalDateTime regDate = LocalDateTime.of(2021, 2,8,0,0);
		NoticeModel notice = new NoticeModel();
		notice.setId(1);
		notice.setTitle("공지사항입니다");
		notice.setContents("공지사항 내용 입니다");
		notice.setRegDate(regDate);
		
		return notice;
	}
	*/
	
	/*
	@GetMapping("/api/notice")
	public List<NoticeModel> getNotices(){
		List<NoticeModel> noticeList = new ArrayList<NoticeModel>();
		NoticeModel notice1 = NoticeModel.builder()
								.id(2)
								.title("공지사항 첫번째 입니다.") 
								.contents("공지사항 첫번째 내용입니다")
								.regDate(LocalDateTime.of(2021, 1,20,0,0))
								.build();
			
		NoticeModel notice2 = NoticeModel.builder()
								.id(1)
								.title("두번째 공지사항 입니다.")
								.contents("룰루랄라")
								.regDate(LocalDateTime.of(2022, 1,30,0,0))
								.build();
		
		noticeList.add(notice1);
		noticeList.add(notice2);
		
		return noticeList;
	}
	*/
	@GetMapping("/api/notice")
	public List<NoticeModel> notice(){
		List<NoticeModel> noticeList = new ArrayList<>();
		
		
		return noticeList;
	}
	@GetMapping("/api/notice/count")
	public int noticeCount() {
		return 10;
	}
	
	/*@PostMapping("/api/notice")
	public NoticeModel addNotice(@RequestParam String title, @RequestParam String contents) {
		NoticeModel notice = NoticeModel.builder()
								.title(title)
								.contents(contents)
								.id(1)
								.regDate(LocalDateTime.now())
								.build();
		return notice;
	}*/
	
	/*
	@PostMapping("/api/notice")
	public NoticeModel addNotice(NoticeModel noticeModel) {
		noticeModel.setId(0);
		noticeModel.setRegDate(LocalDateTime.now());
		return noticeModel;
	}
	
	
	@PostMapping("/api/notice2")
	public NoticeModel addNotice(@RequestBody NoticeModel noticeModel) {
		noticeModel.setId(0);
		noticeModel.setRegDate(LocalDateTime.now());
		
		return noticeModel;
	}
	
	@PostMapping("/api/notice")
	public Notice addNotice(@RequestBody NoticeInput noticeInput) {
		Notice notice = Notice.builder()
							.title(noticeInput.getTitle())
							.contents(noticeInput.getContents())
							.regDate(LocalDateTime.now())
							.build();
		
		noticeRepository.save(notice);
		return notice;
	}
	*/
	@PostMapping("/api/notice")
	public Notice addNotice(@RequestBody NoticeInput noticeInput) {
		Notice notice = Notice.builder()
							.title(noticeInput.getTitle())
							.contents(noticeInput.getContents())
							.hits(0)
							.likes(0)
							.regDate(LocalDateTime.now())
							.build();
		Notice resultNotice = noticeRepository.save(notice);
		return resultNotice;
	}
}
