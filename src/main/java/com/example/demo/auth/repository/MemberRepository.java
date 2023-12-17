package com.example.demo.auth.repository;

import com.example.demo.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberRepository extends JpaRepository<User, Integer>{
	
	List<User> findByEmailAndPwd(String email, String password);

}
