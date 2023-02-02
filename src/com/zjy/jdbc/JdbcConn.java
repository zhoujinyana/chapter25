package com.zjy.jdbc;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConn {

    @Test
    public void connect02() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        //使用反射加载Driver类，动态加载，更加灵活，减少依赖性
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver =(Driver) aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/db01";
        //将用户名密码放在properties对象
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","root");

        Connection connect = driver.connect(url, properties);
        System.out.println("方式二" + connect);
    }

    @Test
    public void connect03() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver =(Driver) aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/db01";
        //将用户名密码放在properties对象
        String user = "root";
        String password = "root";

        DriverManager.registerDriver(driver);
        Connection connect = DriverManager.getConnection(url,user,password);
        System.out.println("第三种方式"+ connect);
    }

    @Test
    //使用最多的方式
    public void connect04() throws ClassNotFoundException, SQLException {
        //在加载Driver类时，完成注册（底层静态代码块在加载类时已经注册过）
        //Class.forName("com.mysql.cj.jdbc.Driver");//这句话不加也可以
        String url = "jdbc:mysql://localhost:3306/db01";
        //将用户名密码放在properties对象
        String user = "root";
        String password = "root";

        Connection connect = DriverManager.getConnection(url,user,password);
        System.out.println("第四种方式"+connect);


    }

    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properites"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("第五种方式"+connection);

    }
}
