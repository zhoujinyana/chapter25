package com.zjy.jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//完成jdbc程序
public class Jdbc01 {
    public static void main(String[] args) throws SQLException {

        //前置工作:在项目下创建一个文件夹比如 libs
        // 将 mysql.jar拷贝到该目录下，点击add to project ..加入到项目中

        //注册驱动
        Driver driver = new Driver();//创建driver对象

        //得到连接
        //(1)jdbc :mysql:// 规定好表示协议，通过jdbc的方式连接mysql
        //(2)localhost 主机，可以是ip地址
        //(3)3306表示mysql监听的端口
        //(4)db01连接到mysql dbms 的哪个数据库
        //(5)mysql的连接本质就是前面学过的socket连接

        String url = "jdbc:mysql://localhost:3306/db01";
        //将用户名密码放在properties对象
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","root");

        Connection connect = driver.connect(url, properties);

        //执行sql
        String sql = "insert into user values(5,'周晋燕','山西省朔州市')";
        //statement用于执行静态SQL语句并返回其生成的结果的对象
        Statement statement = connect.createStatement();
        //如果是 dml语句,返回的就是影响行数
        int rows = statement.executeUpdate(sql);

        System.out.println(rows>0?"成功":"失败");

        //关闭连接
        statement.close();
        connect.close();
    }

}
