package com.webserver.core;

import com.webserver.annotations.Controller;
import com.webserver.annotations.RequestMapping;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/21 16:54
 */
public class HandlerMapping {
    private static Map<String, Method> mapping = new HashMap<>();
    static {
        try {
            initMapping();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private static void initMapping() throws URISyntaxException, ClassNotFoundException {
        //获取DispatcherServlet类所在目录的上级目录
        File dir = new File(HandlerMapping.class.getResource("..").toURI());
        String str = dir.getName();
        //获取Controller所在目录
        File controllerDir = new File(dir, "Controller");
        if(!controllerDir.exists()) return;
        //com.webserver.core
        String corePackage = HandlerMapping.class.getPackage().getName();
        //com.webserver
        String webserverPackage = corePackage.substring(0, corePackage.lastIndexOf('.'));
        //com.webserver.controller
        String controllerPackage = webserverPackage + ".Controller";
        //获取controller目录下所有类，并遍历
        File[] controllers = controllerDir.listFiles(f->f.getName().endsWith(".class"));
        //标记是否找到了所请求业务对应的处理方法
        boolean isFind = false;
        for (File controller : controllers) {
            String fileName = controller.getName();
            String className = fileName.substring(0, fileName.indexOf("."));
            System.out.println(controllerPackage);
            Class controllerCls = Class.forName(controllerPackage + "." + className);
            //如果没有被@Controller标注，就不用往下走了
            if(!controllerCls.isAnnotationPresent(Controller.class)){
                continue;
            }
            //获取所有方法并遍历
            Method[]methods = controllerCls.getMethods();
            for (Method method : methods) {
                //判断方法有没有被@RequestMapping标注
                if(method.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    String value = annotation.value();
                    mapping.put(value, method);
                }
            }
        }
    }

    public static Method getMethod(String name){
        return mapping.get(name);
    }


    public static void main(String[] args)  {

        Method method = getMethod("/regUser");
        System.out.println(method);
    }
}
