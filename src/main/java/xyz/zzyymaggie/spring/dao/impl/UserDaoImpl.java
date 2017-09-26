package xyz.zzyymaggie.spring.dao.impl;

import org.springframework.stereotype.Component;

import xyz.zzyymaggie.spring.dao.UserDao;
import xyz.zzyymaggie.spring.model.User;

@Component("userDaoImpl") 
public class UserDaoImpl implements UserDao {
	public void save(User user) {
		//TODO: save into database or file
		System.out.println("user saved!");
		//throw new RuntimeException("exeption!");
	}

	public void delete() {
		System.out.println("user deleted!");
	}
}
