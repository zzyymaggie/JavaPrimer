package xyz.zzyymaggie.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xyz.zzyymaggie.spring.dao.UserDao;
import xyz.zzyymaggie.spring.model.User;

public class UserService {

	private UserDao userDAO;

	public void init() {
		System.out.println("init");
	}

	public void add(User user) {
		userDAO.save(user);
	}

	public UserDao getUserDAO() {
		return userDAO;
	}

	@Autowired
	public void setUserDAO(@Qualifier("userDaoImpl") UserDao userDAO) {
		this.userDAO = userDAO;
	}

	public void destroy() {
		System.out.println("destroy");
	}
}
