package jdbc;

import java.sql.*;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 14:08
 */
public class JDBCDemo3 {
    public static void main(String []args){
        try(
                Connection con = DBUtil.getConnection();
                )
        {
            Statement state = con.createStatement();
            String sql = "SELECT id, name, age, salary, comm, gender " +
                    "FROM teacher";
            System.out.println(sql);

            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int salary = rs.getInt("salary");
                int comm = rs.getInt("comm");
                String gender = rs.getString("gender");
                System.out.println(id + "," + name + "," + age + "," + salary + "," + comm);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
