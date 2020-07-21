package xyz.zzyymaggie.spring.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.zzyymaggie.spring.javaconfig.service.BookService;
import xyz.zzyymaggie.spring.javaconfig.service.DependencyService;
import xyz.zzyymaggie.spring.javaconfig.service.OtherService;
import xyz.zzyymaggie.spring.javaconfig.service.impl.BookServiceImpl;
import xyz.zzyymaggie.spring.javaconfig.service.impl.DependencyServiceImpl;
import xyz.zzyymaggie.spring.javaconfig.service.impl.OtherServiceImpl;

/**
 * spring annotation demo code
 *
 */
@Configuration
public class JavaConfigDemo {
	private static ApplicationContext context;

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类） -> Spring Bean
		applicationContext.register(JavaConfigDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();
	}

	@Bean
	public BookService bookService() {
		return new BookServiceImpl(dependencyService());
	}

	@Bean
	public OtherService otherService() {
		return new OtherServiceImpl(dependencyService());
	}

	@Bean
	public DependencyService dependencyService() {
		return new DependencyServiceImpl();
	}
}
