package com.zjy.dao_.test;

import com.zjy.dao_.dao.UserDAO;
import com.zjy.dao_.domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDAO {
    @Test
    public void testUserDAO(){
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.queryMulti("select * from user where id >= ?", User.class, 1);
        for(User user : users){
            System.out.println(user);
        }

        int update = userDAO.update("insert into user values(7,?,?)", "小七", "湖南省");
        System.out.println(update > 0 ?"执行成功":"执行没有影响表");


    }
}
