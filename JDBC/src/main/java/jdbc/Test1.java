package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 10:54
 */
public class Test1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                "root"
                ,"root"
        );

        Statement statement = con.createStatement();
        for (int i = 0; i < 100; i++) {
            String name = "test" + (int)(Math.random() * 101);
            int age = 10 + (int)(Math.random() * 6);
            int classNum = 1 + (int)(Math.random() * 10);
            String sql = "INSERT INTO student1(name, age, class_id) VALUES(" +
                    "'" + name + "'," + age + "," + classNum + ")";
            System.out.println(sql);
            statement.executeUpdate(sql);
        }
    }
}
