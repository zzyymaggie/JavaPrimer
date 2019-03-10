package xyz.zzyymaggie.spring.aop.basic.proxy;

import java.lang.reflect.Proxy;

/**
 * @link http://blog.csdn.net/Mr_Tim/article/details/51891202
 * 这个例子来源《精通Spring4.x 企业应用开发实践》第7章 Spring AOP基础。这篇博客是把这一章节整理了一下。
 * @author sophia
 */
public class TestForumService {
    public static void main(String[] args) {
        System.out.println("JDK Proxy Test ...");
        jdkProxyTest();
    }

    public static void jdkProxyTest() {
        ForumService target = new ForumServiceImpl();// ①目标业务类
        // ② 将目标业务类和横切代码编织到一起
        PerformaceHandler handler = new PerformaceHandler(target);
        // ③为编织了目标业务类逻辑和性能监视横切逻辑的handler创建代理类
        ForumService proxy = (ForumService) Proxy.newProxyInstance(target
                .getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
        // ④ 操作代理实例
        proxy.removeForum(10);
        proxy.removeTopic(1012);
    }
}
