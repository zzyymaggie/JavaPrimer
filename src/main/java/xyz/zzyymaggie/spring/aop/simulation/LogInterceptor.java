package xyz.zzyymaggie.spring.aop.simulation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogInterceptor implements InvocationHandler{
	//被代理的对象
	private Object target;
	
	public void beforeMethod() {
		System.out.println("save start...");
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		beforeMethod();
		method.invoke(target, args);
		return null;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
}
