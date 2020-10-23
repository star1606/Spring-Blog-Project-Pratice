package com.cos.springblog2.dto;

import java.sql.Timestamp;

import com.cos.springblog2.model.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {

	private Post board;
	
	// Post
//	private int id;
//	private int userId;
//	private String title;
//	private String content;
//	private Timestamp createDate;
	
	private String username;
	
}
