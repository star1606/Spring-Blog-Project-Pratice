package com.cos.springblog2.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private String role;
	private Timestamp createDate;
}
