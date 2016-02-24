package com.somda.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somda.spring.daos.UserDao;
import com.somda.spring.entity.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public void saveUser(User user) {
		dao.saveUser(user);
	}

	@Override
	public List<User> findAllUser() {
		return dao.findAllUsers();
	}

	@Override
	public void deleteUserByName(String name) {
		dao.deleteUserByName(name);
	}

	@Override
	public User findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public User findByUserName(String userName) throws HibernateException {
		return dao.findByUserName(userName);
	}

	@Override
	public void updateUser(User user) {
		dao.updateUser(user);
	}
}
