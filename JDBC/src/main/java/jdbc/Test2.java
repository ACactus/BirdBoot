package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 11:39
 */
public class Test2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                "root"
                ,"root"
        );
        String sql0 = "DELETE FROM student1 WHERE name LIKE ";
        Statement st = cn.createStatement();
        for (int i = 2; i <= 10 ; i++) {
            String name = "test" +  i + "_";
            String sql = sql0 + "'" + name + "'";
            System.out.println(sql);
            st.execute(sql);
        }
    }
}
