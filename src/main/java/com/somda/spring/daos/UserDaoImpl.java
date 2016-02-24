package com.somda.spring.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.somda.spring.entity.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public void saveUser(User user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria criteria = getSession().createCriteria(User.class);
		return (List<User>) criteria.list();
	}

	@Override
	public void deleteUserByName(String name) {
		Query query = getSession().createSQLQuery("delete from User where name = :name");
		query.setString("name", name);
		query.executeUpdate();
	}

	@Override
	public User findByName(String name) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("name", name));
		return (User) criteria.uniqueResult();
	}

	@Override
	public User findByUserName(String userName) throws HibernateException {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void updateUser(User user) {
		getSession().update(user);
	}
}
