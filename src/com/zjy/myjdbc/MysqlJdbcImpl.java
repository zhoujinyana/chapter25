package com.zjy.myjdbc;

public class MysqlJdbcImpl implements JdbcInterface{

    @Override
    public Object getConnection() {
        System.out.println("得到mysql的连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("完成增删改查");

    }

    @Override
    public void close() {
        System.out.println("关闭连接");

    }
}
