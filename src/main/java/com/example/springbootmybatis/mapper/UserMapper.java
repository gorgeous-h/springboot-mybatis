package com.example.springbootmybatis.mapper;

import java.util.List;
import java.util.Map;

import com.example.springbootmybatis.entity.User;

public interface UserMapper {
	void save(User user);
	void update(User user);
	void delete(int id);
	User findById(int id);
	List<User> findAll();
	List<User> getUsers(Map<String, Object> params);
	long getUsersCount(Map<String, Object> params);
}
