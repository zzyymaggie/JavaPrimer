package xyz.zzyymaggie.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Around 环绕通知
 * @author sophia
 *
 */
public class MyMethodInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("call method before ...");
		Object object = arg0.proceed();
		System.out.println("call method after ...");
		return object;
	}

}
