package Druid_;


import com.zjy.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BDUtils_Use {
    @Test
    public void testQueryMany() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        //引入dbutils jar包
        QueryRunner queryRunner = new QueryRunner();
        //执行相关的方法，返回结果集
        String sql = "select * from user where id >= ?";
        //query方法是执行sql语句，得到resultset封装到arraylist
        //new BeanListHandler<>(User.class)：
        // 在将resultset到User对象封装到arraylist，
        // 底层使用反射去获取User类的属性，然后进行封装
        //1 是给sql语句中的？赋值
        // 底层得到的resultset ,会在query 关闭，也会关闭PreparedStatment
        List<User> list =
                queryRunner.query(connection, sql, new BeanListHandler<>(User.class), 1);
        System.out.println("输出集合信息：");
        for(User user:list){
            System.out.println(user);
        }

        JDBCUtilsByDruid.close(null,null,connection);



    }
    //查询单个记录
    @Test
    public void testQuerySingle() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from user where id = ?";
        User user = queryRunner.query(connection, sql, new BeanHandler<>(User.class), 4);
        System.out.println(user);
        JDBCUtilsByDruid.close(null,null,connection);

    }
    //查询单行记录的某一列
    @Test
    public void testScalar() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select name from user where id = ?";
        //返回的是单行单列对象
        Object obj = queryRunner.query(connection, sql, new ScalarHandler(), 2);
        System.out.println(obj);


        JDBCUtilsByDruid.close(null,null,connection);

    }

    @Test
    //演示dml增删改
    public void testDML() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        String sql = "update user set name = ? where id = ?";
        String sql2 = "insert into user values(?,?,?)";
        String sql3 = "delete from user where id = ?";
        int affectedRow = queryRunner.update(connection, sql3, 3);


        System.out.println(affectedRow > 0?"执行成功":"执行没有影响列表");

        JDBCUtilsByDruid.close(null,null,connection);

    }
}
