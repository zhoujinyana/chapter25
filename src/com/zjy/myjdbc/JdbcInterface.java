package com.zjy.myjdbc;

public interface JdbcInterface {

    //连接
    public Object getConnection();

    public void crud();

    //关闭
    public void close();
}
