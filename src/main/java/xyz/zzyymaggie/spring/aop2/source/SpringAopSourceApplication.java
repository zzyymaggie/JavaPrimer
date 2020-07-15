package xyz.zzyymaggie.spring.aop2.source;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.zzyymaggie.spring.aop2.source.service.*;

/**
 * 参考 {@link https://javadoop.com/post/spring-aop-source}
 */
public class SpringAopSourceApplication {

    public static void main(String[] args) {

        // 启动 Spring 的 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:DefaultAdvisorAutoProxy.xml");

        UserService userService = context.getBean(UserService.class);
        OrderService orderService = context.getBean(OrderService.class);

        userService.createUser("Tom", "Cruise", 55);
        userService.queryUser();

        orderService.createOrder("Leo", "随便买点什么");
        orderService.queryOrder("Leo");
    }
}
