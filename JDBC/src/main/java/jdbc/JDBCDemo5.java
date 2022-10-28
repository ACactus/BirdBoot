package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 16:24
 */
public class JDBCDemo5 {
    public static void main(String[] args) {
        try(
                Connection con = DBUtil.getConnection();
                ) {
            String sql = "INSERT INTO userinfo" +
                    "(username, password, nickname, age) " +
                    "VALUES" +
                    "(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "张金钟");
            ps.setString(2, "123");
            ps.setString(3, "臭宝");
            ps.setInt(4, 80);
            int count = ps.executeUpdate();

            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
