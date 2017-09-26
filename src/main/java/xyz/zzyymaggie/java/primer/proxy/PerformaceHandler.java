package xyz.zzyymaggie.java.primer.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * JDK动态代理
 */
import java.lang.reflect.Method;

import xyz.zzyymaggie.java.primer.nonproxy.PerformanceMonitor;

public class PerformaceHandler implements InvocationHandler {
	private Object target;

	public PerformaceHandler(Object target) {// ①target为目标的业务类
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());
		Object obj = method.invoke(target, args);// ②通过反射方法调用目标业务类的业务方法
		PerformanceMonitor.end();
		return obj;
	}
}