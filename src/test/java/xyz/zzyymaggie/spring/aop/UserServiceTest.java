package xyz.zzyymaggie.spring.aop;

import java.lang.reflect.Proxy;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.zzyymaggie.spring.aop.simulation.LogInterceptor;
import xyz.zzyymaggie.spring.dao.UserDao;
import xyz.zzyymaggie.spring.dao.impl.UserDaoImpl;
import xyz.zzyymaggie.spring.model.User;
import xyz.zzyymaggie.spring.service.UserService;

public class UserServiceTest {
	@Test
	public void testAdd() throws Exception {
		//TODO: explain how to load userService instance
		BeanFactory applicationContext = new ClassPathXmlApplicationContext();
		
		UserService service = (UserService)applicationContext.getBean("userService");
		
		User u = new User();
		u.setUsername("zhangsan");
		u.setPassword("zhangsan");
		service.add(u);
	}
	
	@Test
	public void testProxy() {
		UserDao userDAO = new UserDaoImpl();
		LogInterceptor li = new LogInterceptor();
		li.setTarget(userDAO);
		UserDao userDAOProxy = (UserDao)Proxy.newProxyInstance(userDAO.getClass().getClassLoader(), userDAO.getClass().getInterfaces(), li);
		System.out.println(userDAOProxy.getClass());
		userDAOProxy.delete();
		userDAOProxy.save(new User());
		
	}
	
	/*class $Proxy4 implements UserDAO 
	 * {
	 * 	save(User u) {
	 * 	Method m = UserDAO.getclass.getmethod 
	 * li.invoke(this, m, u)
	 * }
	 * }
	 */
}
