package xyz.zzyymaggie.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring aop demo code
 *
 */
public class App 
{
	private static ApplicationContext context;
	
    public static void main( String[] args )
    {
    		context = getApplicationContext();
    			TestServiceInter ts=(TestServiceInter) context.getBean("proxyFactoryBean");
    			ts.sayHello();
    			((TestServiceInter2)ts).sayBye();
    }
    
    public static ApplicationContext getApplicationContext() {
    		if(context == null) {
    			context = new ClassPathXmlApplicationContext("beans-aop.xml");
    		}
    		return context;
    }
}
