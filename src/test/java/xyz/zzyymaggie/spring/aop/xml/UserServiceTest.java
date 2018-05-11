package xyz.zzyymaggie.spring.aop.xml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.zzyymaggie.spring.model.User;
import xyz.zzyymaggie.spring.service.UserService;

/***
 * AOP 两种实现，xml是常用的方式
 * @author sophia
 *
 */
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
            context = new ClassPathXmlApplicationContext("beans-aop-xml.xml");
        }
        return context;
    }
}
