package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 14:46
 */
public class JDBCDemo4 {
    public static void main(String[] args) throws SQLException {
        try(
                Connection con = DBUtil.getConnection();
                ){
            String sql = "SELECT s.name, s.age, s.gender, c.name";
            ResultSet rs = con.createStatement().executeQuery(sql);

        }
    }
}
