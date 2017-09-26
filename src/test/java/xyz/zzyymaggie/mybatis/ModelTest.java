package xyz.zzyymaggie.mybatis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xyz.zzyymaggie.mybatis.model.User;
import xyz.zzyymaggie.mybatis.util.MyBatisUtil;
 
public class ModelTest {  
   private SqlSession session = null;  
     
   @Before  
   public void before() {  
       session = MyBatisUtil.getSession();  
   }  
   
   @After  
   public void after() {  
       session.commit();  
       session.close();  
   }  
     
   @Test  
   public void testGetSession() {  
       assertNotNull(session);
   }  
     
   @Test  
   public void testSelectUser() {  
       List<User> userList = session.selectList("xyz.zzyymaggie.mybatis.dao.mapper.UserMapper.selectUser");  
         
       for(User each : userList) {  
           System.out.println("each->" + each);  
       }  
         
   }  
     
   @Test  
   public void testInsert() {  
       User user = new User();  
       user.setName("Sophia1"); 
       user.setAge(23);  
       user.setGender(0);  
       user.setEmail("sophia@7th.cn");
         
       int result = session.insert("xyz.zzyymaggie.mybatis.dao.mapper.UserMapper.insert" , user);  
       assertEquals(1 , result);  
   }  
}  