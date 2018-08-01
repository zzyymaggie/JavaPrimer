package xyz.zzyymaggie.mybatis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xyz.zzyymaggie.mybatis.dao.mapper.StyleDao;
import xyz.zzyymaggie.mybatis.model.Style;
import xyz.zzyymaggie.mybatis.model.User;
import xyz.zzyymaggie.mybatis.util.MyBatisUtil;
 
/**
 * From case1 ~ case4, we can conclude that we should use the batch action for multiple records to improve performance. 
 * @author sozhang
 *
 */
public class ModelTest {  
   private SqlSession session = null;  
   private Connection conn = null;
   private final static int SELECT_MAX_COUNT = 100000;
     
   @Before  
   public void before() {  
       session = MyBatisUtil.getSession();  
       conn = MyBatisUtil.getConnection();
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
   
   /**
    * case1: select all records at one time => about 15000ms
    */
   @Test  
   public void testSelectListByMybatis() {  
       long start = System.currentTimeMillis();
       List<Style> styleList = session.selectList("xyz.zzyymaggie.mybatis.dao.mapper.StyleMapper.selectStyleList", SELECT_MAX_COUNT);  
       System.out.println("record num:" + styleList.size());
       System.out.println("[testSelectListByMybatis]It spends " + (System.currentTimeMillis() - start) + "ms");
   }  
   
   /**
    * case2: batch select records by fetchSize = 1000 => about 700ms
    */
   @Test  
   public void testBatchSelectListByMybatis() {  
       long start = System.currentTimeMillis();
       List<Style> styleList = session.selectList("xyz.zzyymaggie.mybatis.dao.mapper.StyleMapper.batchSelectStyleList", SELECT_MAX_COUNT);  
       System.out.println("record num:" + styleList.size());
       System.out.println("[testBatchSelectListByMybatis]It spends " + (System.currentTimeMillis() - start) + "ms");
   }  
   
   /**
    * case3: select all records at one time => about 15000ms
    */
   @Test  
   public void testSelectListByJdbc() {
       long start = System.currentTimeMillis();
       StyleDao styleDao = new StyleDao();
       List<Style> styleList = styleDao.getAll(conn, SELECT_MAX_COUNT);  
       System.out.println("record num:" + styleList.size());
       System.out.println("[testSelectListByJdbc]It spends " + (System.currentTimeMillis() - start) + "ms");
   }
     
   /**
    *  cas4: batch select records by fetchSize = 1000 => about 700ms
    */
   @Test  
   public void testBatchSelectListByJdbc() {
       long start = System.currentTimeMillis();
       StyleDao styleDao = new StyleDao();
       List<Style> styleList = styleDao.batchGetAll(conn, SELECT_MAX_COUNT);  
       System.out.println("record num:" + styleList.size());
       System.out.println("[testBatchSelectListByJdbc]It spends " + (System.currentTimeMillis() - start) + "ms");
   }
   
    @Test
    public void testSelectUser() {
        List<User> userList = session.selectList("xyz.zzyymaggie.mybatis.dao.mapper.UserMapper.selectUser");

        for (User each : userList) {
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

        int result = session.insert("xyz.zzyymaggie.mybatis.dao.mapper.UserMapper.insert", user);
        assertEquals(1, result);
    }
}  