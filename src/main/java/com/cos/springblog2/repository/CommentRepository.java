package com.cos.springblog2.repository;

import java.util.List;

import com.cos.springblog2.dto.ReplyResponseDto;
import com.cos.springblog2.model.Post;

public interface CommentRepository {


	// 여러 개의 Reply를 가져와야하기 때문에 List를 쓴다
	List<ReplyResponseDto> findAll(int postId);
	
	
}
