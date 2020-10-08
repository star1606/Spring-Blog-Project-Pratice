package com.cos.springblog2.repository;

import com.cos.springblog2.model.User;

public interface UserRepository {

	public void save(User user);
	
	// 아직까지 매개변수의 User넣는 것과 데이터 타입에 User넣는 때를 정확히 구분 못하겠다.
	public User findByUsernameAndPassword(User user);
	
	public User findByUsername(String username);
	//public User findByUsername(User user);
}
