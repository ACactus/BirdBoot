package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 16:47
 */
public class Test4 {
    public static void main(String[] args) throws SQLException {
        Connection con = DBUtil.getConnection();
        String sql = "UPDATE userinfo " +
                "SET password=? " +
                "WHERE username=? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "66666");
        ps.setString(2, "张金钟");
        System.out.println(ps.toString());;

        int count = ps.executeUpdate();
        System.out.println(count);

    }
}
