package Druid_;


import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCUtilsByDruid_Use {
    @Test
    public void testSelect(){
        System.out.println("使用druid");
        Connection connection = null;
        String sql = "select * from user";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                System.out.println(id +"\t" + name + "\t" + address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }

    }

    @Test
    //使用土方法来解决resultset封装到arraylist
    public void testSelectToArrayList(){
        System.out.println("使用druid");
        Connection connection = null;
        String sql = "select * from user";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<User> list = new ArrayList<User>();
        try {
            connection = JDBCUtilsByDruid.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                //System.out.println(id +"\t" + name + "\t" + address);

                //把resultset的记录，封装到user对象，放入到list
                list.add(new User(id,name,address));
            }

            System.out.println("list集合数据" + list);
            for(User user:list){
                System.out.println(user.getName());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }

    }

}
