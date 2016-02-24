package com.somda.spring.daos;

import java.util.List;

import com.somda.spring.entity.User;

public interface UserDao {

	void saveUser(User user);

	List<User> findAllUsers();

	void deleteUserByName(String name);

	User findByName(String name);

	User findByUserName(String userName);

	void updateUser(User user);
}
