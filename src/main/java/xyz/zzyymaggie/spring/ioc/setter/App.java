package xyz.zzyymaggie.spring.ioc.setter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring ioc setter注入 循环依赖 demo
 * 没有问题！
 *
 */
public class App {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = getApplicationContext();
		StudentService studentService = context.getBean("studentService", StudentService.class);
		studentService.run();
	}

	public static ApplicationContext getApplicationContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("spring-ioc-setter.xml");
		}
		return context;
	}
}
