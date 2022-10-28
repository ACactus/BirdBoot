package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 10:46
 */
public class JDBCDemo2 {
    public static void main(String []args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                "root"
                ,"root"
        );
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO student1(" +
                "name, age, class_id) " +
                "VALUES('张三', 12,1)";
        int count = statement.executeUpdate(sql);
        if(count > 0){
            System.out.println("影响了表中" + count + "项数据");
        }
    }
}
