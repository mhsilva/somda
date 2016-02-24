package com.somda.spring.services;

import java.util.List;

import com.somda.spring.entity.User;

public interface UserService {

	void saveUser(User user);

	List<User> findAllUser();

	void deleteUserByName(String name);

	User findByName(String name);

	User findByUserName(String userName);

	void updateUser(User user);

}
