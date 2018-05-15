package xyz.zzyymaggie.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisFactory {
    private static SqlSessionFactory sqlSessionFactory = null;  
    private static ThreadLocal<SqlSession> localSessions = new ThreadLocal<SqlSession>();
    /** 
     * 初始化Session工厂 
     * @throws IOException 
     */  
    private static void initialFactory() throws IOException {  
        String resource = "mybatis-config.xml";  
        InputStream inputStream = Resources.getResourceAsStream(resource);  
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);  
    }  
      
     /**
      * 获取Session
      * 
      * @return
      */
     public static SqlSession getSession() {
         SqlSession session = localSessions.get();
         if(session == null) {
             if (sqlSessionFactory == null) {
                 try {
                     initialFactory();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             session = sqlSessionFactory.openSession();
             localSessions.set(session);
         }
         return session;
     }
}
