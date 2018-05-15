package xyz.zzyymaggie.mybatis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;  
 
/** 
* MyBatis的工具类 
* @author zzyymaggie 
* 
*/  
public class MyBatisUtil {  
   private static SqlSessionFactory sqlSessionFactory = null;  
   private static Connection connection = null;
   
     
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
        if (sqlSessionFactory == null) {
            try {
                initialFactory();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory.openSession();
    }
     
    public static Connection getConnection() {
        if(connection == null) {
            connection = initConnection();
        }
        return connection;
    }
    
    private static Connection initConnection() {
        String configPath = System.getProperty("user.dir") + File.separator + "src/main/resources/config.properties";
        Properties properties = readProperties(configPath);
        if (properties == null) {
            return null;
        }
        String driver = properties.getProperty("connections.driver");
        String url = properties.getProperty("connections.url");
        String username = properties.getProperty("connections.user");
        String password = properties.getProperty("connections.password");
        Connection conn = null;
        
        try {
            Class.forName(driver); 
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static Properties readProperties(String filename) {
        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}  
