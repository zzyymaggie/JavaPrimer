package xyz.zzyymaggie.spring.ioc.ctor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring ioc 构造器注入 循环依赖 demo
 * 死循环！
 */
public class App {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = getApplicationContext();
	}

	public static ApplicationContext getApplicationContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("spring-ioc-ctor.xml");
		}
		return context;
	}
}
