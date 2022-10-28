package com.webserver.core;

import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {

        String path = request.getRequestURI();
        File file = new File(staticDir, path);

        Method method = HandlerMapping.getMethod(path);
        if(method != null){
            Class cls = method.getDeclaringClass();
            Object o = cls.newInstance();
            method.invoke(o, request, response);
            return;
        }
        //判断当前请求路径是否对应一个业务处理h
        if (file.isFile()) {
            response.setContentFile(file);
        } else {
            file = new File(staticDir, "404.html");
            response.setStatusCode(404);
            response.setStatusReason("NotFound");
            response.setContentFile(file);
        }
        response.addHeader("Server", "BirdWebServer");
    }
}
