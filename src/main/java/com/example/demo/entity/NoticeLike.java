package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeLike {
	@Id
	private long id;
	
	@JoinColumn
	@ManyToOne
	private Notice notice;
	
	@JoinColumn
	@ManyToOne
	private User user;
	
}
