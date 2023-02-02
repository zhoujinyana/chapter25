package com.zjy;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatementDML_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        String name = scanner.next();
        String address = scanner.next();

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properites"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "insert into user values(?,?,?)" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,address);



        int rows = preparedStatement.executeUpdate();
        System.out.println(rows > 0?"执行成功":"执行失败");




        preparedStatement.close();
        connection.close();
    }
}
