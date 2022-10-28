package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 9:03
 */
public class JDBCDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                "root"
                ,"root"
        );

        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE userinfo(id INT AUTO_INCREMENT PRIMARY KEY , username VARCHAR(30), password VARCHAR(30), nickname VARCHAR(30), age INT(3));";
        statement.execute(sql);
        System.out.println("连接成功！");

    }
}
