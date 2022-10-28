package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 10:18
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true"
                ,"root", "root"
        );
        Statement statement = con.createStatement();
        String sql = "CREATE TABLE student1(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(30)," +
                "age INT," +
                "class_id INT)";
        statement.execute(sql);



    }
}
