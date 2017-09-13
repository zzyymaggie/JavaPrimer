package xyz.zzyymaggie.java.primer.proxy;

import java.lang.reflect.Proxy;

/**
 * @link http://blog.csdn.net/Mr_Tim/article/details/51891202
 * @author sophia
 */
public class TestForumService {
    public static void main(String[] args) {
        System.out.println("JDK Proxy Test ...");
        jdkProxyTest();
        System.out.println("\ncglib Proxy Test ...");
        cglibProxyTest();
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
    
    public static void cglibProxyTest() {
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService = // ① 通过动态生成子类的方式创建代理对象
        (ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1023);
    }
}
