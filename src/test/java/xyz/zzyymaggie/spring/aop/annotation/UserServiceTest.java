package xyz.zzyymaggie.spring.aop.annotation;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.zzyymaggie.spring.model.User;
import xyz.zzyymaggie.spring.service.UserService;

public class UserServiceTest {
    private static ClassPathXmlApplicationContext context;
    
	@Test
	public void testAdd() throws Exception {
	    context = getApplicationContext();
		UserService service = (UserService)context.getBean("userService");
		
		User u = new User();
		u.setUsername("zhangsan");
		u.setPassword("zhangsan");
		service.add(u);
		context.destroy();
	}
	
	public static ClassPathXmlApplicationContext getApplicationContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("beans-aop-annotation.xml");
        }
        return context;
    }
}
