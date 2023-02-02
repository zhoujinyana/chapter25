import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@SuppressWarnings({"all"})
public class ResultSet_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properites"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String sql = "select id,name,address from user";

        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){//行
           int id =  resultSet.getInt(1);//列
           String name = resultSet.getString(2);//列
           String address = resultSet.getString(3);//列
            System.out.println(id + "\t" + name +"\t" + address);
        }


        resultSet.close();
        statement.close();
        connection.close();
    }
}
