package com.cos.springblog2.repository;

import java.util.List;


import com.cos.springblog2.dto.BoardResponseDto;
import com.cos.springblog2.model.Post;

public interface PostRepository {

	// 여러 개의 Post를 가져와야 하기 때문에 List를 이용해야한다.
	public List<Post> findAll();
	
	public BoardResponseDto findById(int id);
	public void save(Post post);
	
	
}
