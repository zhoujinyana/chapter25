package com.zjy.transaction_;

import com.zjy.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    @Test
    //在默认情况下，connection是默认自动提交
    public  void notransaction() {
        Connection connection = null;
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();

            //int i = 1 / 0;

            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
            preparedStatement1.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,preparedStatement,connection);
        }

    }
    @Test
    //使用事务
    public void transaction_() throws SQLException {
        Connection connection = null;
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);//开启事务
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();

            //int i = 1 / 0;

            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
            preparedStatement1.executeUpdate();

            //提交事务
            connection.commit();


        } catch (SQLException e) {
            //这里可以进行回滚，默认回滚到事务开始的状态
            System.out.println("执行发生异常，回滚");
            connection.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,preparedStatement,connection);
        }

    }

    }

