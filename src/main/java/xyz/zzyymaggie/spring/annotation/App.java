package xyz.zzyymaggie.spring.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.zzyymaggie.spring.model.User;
import xyz.zzyymaggie.spring.service.UserService;

/**
 * spring annotation demo code
 *
 */
public class App {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = getApplicationContext();
		UserService service = (UserService)context.getBean("userService");
		service.add(new User());
		
		service.destroy();
	}

	public static ApplicationContext getApplicationContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("beans-annotation.xml");
		}
		return context;
	}
}
