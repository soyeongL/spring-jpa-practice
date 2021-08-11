package com.example.demo.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.user.model.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String email;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String phone;
	
	@Column
	private LocalDateTime regDate;
	
	@Column
	private LocalDateTime updateDate;
	
	@Column
	private UserStatus status;
	@Column
	private boolean lockYn;
}
