package com.example.demo.notice.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Notice;
import com.example.demo.notice.exception.AlreadyDeletedException;
import com.example.demo.notice.exception.NoticeNotFoundException;
import com.example.demo.notice.model.NoticeDeleteInput;
import com.example.demo.notice.model.NoticeInput;
import com.example.demo.notice.model.NoticeModel;
import com.example.demo.notice.model.ResponseError;
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
	public ResponseEntity<Object> addNotice(@RequestBody @Valid NoticeInput noticeInput
			, Errors errors) {
		if(errors.hasErrors()) {
			List<ResponseError> responseErrors = new ArrayList<>();
			errors.getAllErrors().stream().forEach(e->{
				responseErrors.add(ResponseError.of((FieldError)e));
			});
			return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
		}
		Notice notice = Notice.builder()
							.title(noticeInput.getTitle())
							.contents(noticeInput.getContents())
							.hits(0)
							.likes(0)
							.regDate(LocalDateTime.now())
							.build();
		noticeRepository.save(notice);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/api/notice/{id}")
	public Notice getNotice(@PathVariable Long id) {
		Optional<Notice> notice = noticeRepository.findById(id);
		if(notice.isPresent()) {
			return notice.get();
		}
		return null;
	}
	/*
	@PutMapping("/api/notice/{id}")
	public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
		Optional<Notice> notice=  noticeRepository.findById(id);
		if(notice.isPresent()) {
			notice.get().setTitle(noticeInput.getTitle());
			notice.get().setContents(noticeInput.getContents());
			notice.get().setUpdateDate(LocalDateTime.now());
			noticeRepository.save(notice.get());
		}
		
	}
	*/
	@ExceptionHandler(NoticeNotFoundException.class)
	public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AlreadyDeletedException.class)
	public ResponseEntity<String> alreadyDeletedException(AlreadyDeletedException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
	}
	
	@PutMapping("/api/notice/{id}")
	public void updateNotice(@PathVariable Long id , @RequestBody NoticeInput noticeInput) {
		/*
		Optional<Notice> notice = noticeRepository.findById(id);
		if(!notice.isPresent()) {
			throw new NoticeNotFoundException("공지사항 글이 존재하지 않습니다.");
		}*/
		//정상 로직
		//공지사항 글이 있을 때
		Notice notice = noticeRepository.findById(id)
			.orElseThrow(()-> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다"));
		 
		notice.setTitle(noticeInput.getTitle());
		notice.setContents(noticeInput.getContents());
		notice.setUpdateDate(LocalDateTime.now());
		noticeRepository.save(notice);
	}
	
	@PatchMapping("/api/notice/{id}/hits")
	public void noticeHits(@PathVariable Long id) {
		Notice notice = noticeRepository.findById(id)
			.orElseThrow(()->new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
		notice.setHits(notice.getHits()+1);
		noticeRepository.save(notice);
	}
	/*
	@DeleteMapping("/api/notice/{id}")
	public void deleteNotice(@PathVariable Long id) {
		Notice notice = noticeRepository.findById(id)
								.orElseThrow(()-> new NoticeNotFoundException("공지글 노노"));
		noticeRepository.delete(notice);
	}
	*/
	
	@DeleteMapping("/api/notice/{id}")
	public void deleteNotice(@PathVariable Long id) {
		Notice notice = noticeRepository.findById(id)
			.orElseThrow(()-> new NoticeNotFoundException("공지사항 글 없음"));
		if(notice.isDeleted()) {
			throw new AlreadyDeletedException("이미 삭제된 공지사항의 글입니다.");
		}
		notice.setDeleted(true);
		notice.setDeletedDate(LocalDateTime.now());
		noticeRepository.save(notice);
	}
	
	@DeleteMapping("/api/notice")
	public void deleteNoticeList(@RequestBody NoticeDeleteInput noticeDeleteInput) {
		List<Notice> noticeList = 
		noticeRepository.findByIdIn(noticeDeleteInput.getIdList())
			.orElseThrow(()-> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));
		noticeList.stream().forEach(e->{
			e.setDeleted(true);
			e.setDeletedDate(LocalDateTime.now());
		});
		noticeRepository.saveAll(noticeList);
	}
	
	@DeleteMapping("/api/notice/all")
	public void deleteAll() {
		noticeRepository.deleteAll();
	}
	
	@GetMapping("/api/notice/latest/{size}")
	public Page<Notice> noticeLatest(@PathVariable int size){
		Page<Notice> noticeList = noticeRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "regDate"));
		return noticeList;
	}
	
}
