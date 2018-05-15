package xyz.zzyymaggie.mybatis.util;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.ibatis.session.SqlSession;

import xyz.zzyymaggie.mybatis.model.User;
 
/**
 * Mybatis 二级缓存基于statement,默认是关闭的。一级缓存基于session，默认是开启的。如果要禁用，select节点添加这两个参数useCache="false" flushCache="true"
 * @author sozhang
 *
 */
public class MybatisCacheDemo {  
    
    private ExecutorService exe;  
    private static final int POOL_SIZE = 2;  
      
    public MybatisCacheDemo() {  
        exe = Executors.newFixedThreadPool(POOL_SIZE);  
    }  
    
    public static void main(String[] args) {
        MybatisCacheDemo demo = new MybatisCacheDemo();
        demo.testSelectUser();
        demo.testInsert();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //If the thread is reused before thread, the select result will be cached, that is to say it cannot get the latest result.
        demo.testSelectUser();
        demo.testSelectUser();
    }
    
    public void testSelectUser() {
        exe.execute(new Runnable(){
            public void run(){
                SqlSession session = MybatisFactory.getSession();
                System.out.println("[Thread " + Thread.currentThread().getId() + "]:" + session);
                List<User> userList = session.selectList("xyz.zzyymaggie.mybatis.dao.mapper.UserMapper.selectUser");

                for (User each : userList) {
                    System.out.println("[Thread " + Thread.currentThread().getId() + "]" + " each->" + each);
                }
            }
        });
    }
    
    public void testInsert() {
        SqlSession session = MybatisFactory.getSession();
        User user = new User();
        Random random = new Random(System.currentTimeMillis());
        user.setName("Sophia" + random.nextInt(100));
        user.setAge(23);
        user.setGender(0);
        user.setEmail("sophia@7th.cn");
        
        int result = session.insert("xyz.zzyymaggie.mybatis.dao.mapper.UserMapper.insert", user);
        session.commit();
        session.close();
    }
}  