package com.zjy.myjdbc;

public class TestJDBC {
    public static void main(String[] args) {

        JdbcInterface jdbcInterface = new MysqlJdbcImpl();
        //通过接口调用实现类
        jdbcInterface.getConnection();

    }
}
