package xyz.zzyymaggie.spring.aop.basic.cglib;

import xyz.zzyymaggie.spring.aop.basic.proxy.ForumService;
import xyz.zzyymaggie.spring.aop.basic.proxy.ForumServiceImpl;
import xyz.zzyymaggie.spring.aop.basic.proxy.PerformaceHandler;

import java.lang.reflect.Proxy;

/**
 * @link http://blog.csdn.net/Mr_Tim/article/details/51891202
 * 这个例子来源《精通Spring4.x 企业应用开发实践》第7章 Spring AOP基础。这篇博客是把这一章节整理了一下。
 * @author sophia
 */
public class TestForumService {
    public static void main(String[] args) {
        System.out.println("\ncglib Proxy Main ...");
        cglibProxyTest();
    }

    public static void cglibProxyTest() {
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService = // ① 通过动态生成子类的方式创建代理对象
        (ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1023);
    }
}
