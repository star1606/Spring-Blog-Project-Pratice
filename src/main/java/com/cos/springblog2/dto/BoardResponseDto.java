package com.cos.springblog2.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {

	// Post
	private int id;
	private int userId;
	private String title;
	private String content;
	private Timestamp createDate;
	
	private String username;
	
}
