package com.webserver.controller;

import com.webserver.Util.DBUtil;
import com.webserver.annoations.Controller;
import com.webserver.annoations.RequestMapping;
import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 当前类用于处理与用户相关操作
 */
@Controller
public class UserController {
    private static File userDir;//用来表示存放所有用户信息的目录
    static {
        userDir = new File("./users");
        if(!userDir.exists()){
            userDir.mkdirs();
        }
    }

    @RequestMapping("/loginUser")
    public void login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
            System.out.println("开始处理登录!!!");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username+","+password);
            //必要的验证工作
            if(username==null||username.trim().isEmpty()||
                    password==null||password.trim().isEmpty()){
                response.sendRedirect("login_info_error.html");
                return;
            }

            try(
                    Connection con = DBUtil.getConnection();
                    )
            {
                Statement st = con.createStatement();
                String sql = "SELECT id, username, password, nickname, age " +
                        "FROM userinfo " +
                        "WHERE username='" + username + "' " +
                        "AND password='" + password + "' ";
                ResultSet rs = st.executeQuery(sql);
                if(rs.next()){
                    response.sendRedirect("/login_success.html");
                }else{
                    response.sendRedirect("/login_fail.html");
                }
            }
    }


    @RequestMapping("/regUser")
    public void reg(HttpServletRequest request, HttpServletResponse response){
        System.out.println("开始处理用户注册!!!");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println(username+","+password+","+nickname+","+ageStr);
        if(username==null||username.trim().isEmpty()||
           password==null||password.trim().isEmpty()||
           nickname==null||nickname.trim().isEmpty()||
           ageStr==null||ageStr.trim().isEmpty()||
           !ageStr.matches("[0-9]+")
        ){
            //信息输入有误提示页面
            response.sendRedirect("/reg_info_error.html");
            return;
        }

        System.out.println(username+","+password+","+nickname+","+ageStr);

        int age = Integer.parseInt(ageStr);
        //2
        User user = new User(username,password,nickname,age);
        //参数1:userDir表示父目录 参数2:userDir目录下的子项
        File file = new File(userDir,username+".obj");
        if(file.exists()){//文件存在则说明该用户已经注册过了
            response.sendRedirect("/have_user.html");
            return;
        }
        try (
                Connection con = DBUtil.getConnection();
        ){
            Statement st = con.createStatement();
            String sql = "INSERT INTO userinfo" +
                    "(username, password, nickname, age) " +
                    "VALUES(" +
                    "'" + username + "'," + "'" + password + "', " + "'" + nickname + "'," + age + ")";
            System.out.println(sql);
            st.executeUpdate(sql);
            //利用响应对象要求浏览器访问注册成功页面
            response.sendRedirect("/reg_success.html");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
