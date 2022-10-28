package com.webserver.core;

import com.webserver.Controller.UserController;
import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/18 9:11
 */
public class DispatcherServlet {
    public static File root;
    public static File staticDir;
    private static DispatcherServlet servlet = new DispatcherServlet();
    private DispatcherServlet(){};
    public static DispatcherServlet getInstance(){
        return servlet;
    }

    static {
        try {
            root = new File(DispatcherServlet.class.getClassLoader().getResource(".").toURI());
            staticDir = new File(root, "static");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = request.getRequestURI();
        File file = new File(staticDir, path);

        //判断当前请求路径是否对应一个业务处理h
        if ("/regUser".equals(path)) {
            UserController userController = new UserController();
            userController.reg(request, response);
        }else if("/loginUser".equals(path)){
            UserController controller = new UserController();
            controller.login(request, response);
        }
        else {
            if (file.isFile()) {
                response.setContentFile(file);
            } else {
                file = new File(staticDir, "404.html");
                response.setStatusCode(404);
                response.setStatusReason("NotFound");
                response.setContentFile(file);
            }
        }
        response.addHeader("Server", "BirdWebServer");
    }
}
