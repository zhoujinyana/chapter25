package com.zjy.C3P0_;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class C3P0_ {
@Test
    public void testC3P0_01() throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properites"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        //给数据源 comboPooledDataSource 设置相关的参数
        //注意：连接管理是由comboPooledDataSource管理
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        //设置初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        //设置最大连接数
        comboPooledDataSource.setMaxPoolSize(50);
        //从database接口实现的
        Connection connection = comboPooledDataSource.getConnection();
        System.out.println("连接成功");

        connection.close();



    }
    @Test
    public void testC3P0_02() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("zjy_jdbc");
        Connection connection = comboPooledDataSource.getConnection();
        System.out.println("连接ok");
        connection.close();

    }
}
