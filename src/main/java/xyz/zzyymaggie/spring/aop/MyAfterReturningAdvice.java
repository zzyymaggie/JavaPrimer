package xyz.zzyymaggie.spring.aop;

import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.aop.AfterReturningAdvice;

/**
 * 后置通知
 * @author sophia
 *
 */
public class MyAfterReturningAdvice implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		Date nowDate = new Date();
		System.out.println("[" + nowDate.toString() + "] release resources:" + method.getName());
	}

}
