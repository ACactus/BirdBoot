package com.webserver.Controller;

import com.webserver.annotations.Controller;
import com.webserver.annotations.RequestMapping;
import com.webserver.core.DispatcherServlet;
import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.*;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/19 10:24
 */

@Controller
public class UserController {
    private final static File userDir;
    static {
        userDir = new File(DispatcherServlet.staticDir, "user");
        if(!userDir.exists())
        {
            userDir.mkdirs();
        }
        System.out.println(userDir.toString());
    }

    @RequestMapping("/regUser")
    public void reg(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        String passwd = request.getParameter("passwd");
        String nickName = request.getParameter("nickName");
        String ageStr = request.getParameter("age");
        System.out.println(userName + ", " + passwd + ", " + nickName + ", " + ageStr);

        if(userName == null || userName.trim().isEmpty()||
        passwd == null || passwd.trim().isEmpty()||
        nickName == null || nickName.trim().isEmpty()||
        ageStr == null || ageStr.trim().isEmpty()||
        !ageStr.matches("[0-9]+")){
            response.sendRedirect("/reg_info_error.html");
        }
        User u = new User(userName, passwd, nickName, Integer.parseInt(ageStr));
        try {
            File userFile = new File(userDir, userName + ".obj");
            if(userFile.exists()){
                response.sendRedirect("/have_user.html");
                return;
            }
            FileOutputStream fos = new FileOutputStream(userFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(u);
            response.sendRedirect("/reg_success.html");
//            FileInputStream fis = new FileInputStream(userFile);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            User u2 = (User) ois.readObject();
//            System.out.println(u2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/loginUser")
    public void login(HttpServletRequest request, HttpServletResponse response){
        System.out.println("开始处理用户登录！");
        String userName = request.getParameter("userName");
        String passwd = request.getParameter("passwd");
        String nickName = request.getParameter("nickName");
        System.out.println("userName: " + userName + "," + "passwd:"+ passwd + "," + "nickName:" + nickName);
    }
}
