package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 17:17
 */
public class JDBCDemo6 {
    public static void main(String[] args) {
        try(
                Connection con = DBUtil.getConnection();
                )
        {
            String sql = "SELECT id, username, password, nickname, age  FROM userinfo WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "张金钟");
            ps.setString(2, "66666");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("登陆成功");
            }else{
                System.out.println("登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
