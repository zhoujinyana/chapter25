package com.zjy;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class PreparedStatement_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properites"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "select name,address from user where name = ? and address = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,"jack");
        preparedStatement.setString(2,"山西");



        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {//如果查询到一条记录，则
            System.out.println("恭喜，登录成功");
        }else {
            System.out.println("对不起，登录失败");
        }


        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
