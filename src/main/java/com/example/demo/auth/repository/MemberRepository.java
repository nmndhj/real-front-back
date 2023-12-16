package com.example.demo.auth.repository;

import com.example.demo.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<User, Integer>{
	
	User findByEmailAndPwd(String email, String password);

}
