package xyz.zzyymaggie.spring.aop.basic.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import xyz.zzyymaggie.spring.aop.basic.nonproxy.PerformanceMonitor;

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz); //①设置需要创建子类的类
        enhancer.setCallback(this);
        return enhancer.create(); //②通过字节码技术动态创建子类实例
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //intercept：拦截目标类所有方法的调用
        PerformanceMonitor.begin(obj.getClass().getName() + "." + method.getName());
        Object result = proxy.invokeSuper(obj, args); //③通过代理类调用父类中的方法
        PerformanceMonitor.end();
        return result;
    }
}

