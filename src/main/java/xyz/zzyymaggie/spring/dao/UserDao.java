package xyz.zzyymaggie.spring.dao;

import xyz.zzyymaggie.spring.model.User;

public interface UserDao {

	public void save(User user);

	public void delete();
}
