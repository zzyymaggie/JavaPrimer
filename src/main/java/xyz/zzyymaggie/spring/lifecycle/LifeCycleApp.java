package xyz.zzyymaggie.spring.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.zzyymaggie.spring.model.User;
import xyz.zzyymaggie.spring.service.UserService;

/**
 * spring annotation demo code
 *
 */
public class LifeCycleApp {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = getApplicationContext();
		UserService service = (UserService)context.getBean("userService");
		service.add(new User());
		if(context instanceof ConfigurableApplicationContext) {
			ConfigurableApplicationContext.class.cast(context).close();
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					//do something
					System.out.println("The JVM Hook is executing");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static ApplicationContext getApplicationContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("beans-lifecycle.xml");
		}
		return context;
	}
}
