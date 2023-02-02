package com.zjy;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    //定义相关的属性(6个)，因为只需要一份，因此，我们做出static
    private static String user;//用户名
    private static String password;//密码
    private static String url; //url
    private static String driver; //驱动名

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\mysql.properites"));
            //读取相关的属性值
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");

        } catch (IOException e) {
            //在实际开发中，我们可以这样处理
            // 1．将编译异常转成运行异常
            //2．这是调用者，可以选择捕获该异常，也可以选择默认处理该异常，比较方便.
            throw new RuntimeException(e);

        }

    }

    //连接数据库，返回Connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    //关闭相关资源
    /*
        1.ResultSet结果集
        2. Statement或者PreparedStatement3.Connection
        4。如果需要关闭资源，就传入对象，否则传入null
     */

    public static void close(ResultSet set, Statement statement, Connection connection) {
        //判断是否为null
        //判断是否为null
        if(set != null) {
            try {
                set.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    }
}
