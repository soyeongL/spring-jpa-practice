package com.example.demo.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository< User, Long>{
	int countByEmail(String email);
	Optional<User> findByIdAndPassword(long id, String password);
	Optional<User> findByUserNameAndPhone(String userName, String phone);
	Optional<User> findByEmail(String email);
}
