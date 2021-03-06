package xyz.zzyymaggie.spring.aop;

import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 前置通知
 * @author sophia
 *
 */
public class MyMethodBeforeAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("called method:" + method + ", parameters:" + args);
		Date nowDate = new Date();
		System.out.println("log info: [" + nowDate.toString() + "] called method name:" + method.getName());
	}

}
