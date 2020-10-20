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
public class ReplyResponseDto {

	private Post board;
	
	private int id;
	private int userId;
	private int postId;
	private String content;
	private Timestamp createDate;
	
	private String username;
}
