package com.example.demo.auth.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String userId;

	private String email;
	
	private String pwd;

}
