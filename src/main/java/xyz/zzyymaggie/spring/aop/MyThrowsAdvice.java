package xyz.zzyymaggie.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

/**
 * 异常通知
 * @author sophia
 *
 */
public class MyThrowsAdvice implements ThrowsAdvice {
	public void afterThrowing(Method m, Object[] os, Object target, Exception e) {
		System.out.println("it occurs exception: " + e.getMessage());
	}
}
