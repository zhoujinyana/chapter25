package com.zjy.batch_;

import com.zjy.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Batch_ {
    @Test
    public void noBatch() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i =0; i < 500; i++) {
            preparedStatement.setString(1,"jack" + i);
            preparedStatement.setString(2,"666");
            preparedStatement.executeUpdate();
        }
        JDBCUtils.close(null,preparedStatement,connection);

    }
    @Test
    public void batch() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i =0; i < 500; i++) {
            preparedStatement.setString(1,"jack" + i);
            preparedStatement.setString(2,"666");
            preparedStatement.addBatch();
            if((i + 1)%100 == 0){
                preparedStatement.executeUpdate();
                preparedStatement.clearBatch();
            }
        }
        JDBCUtils.close(null,preparedStatement,connection);


    }
}
