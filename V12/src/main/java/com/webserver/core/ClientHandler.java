package com.webserver.core;


import com.webserver.http.EmptyRequestException;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * 当前线程任务负责与指定的客户端进行交互.
 * 这里的交互规则要遵从HTTP协议的交互要求,以客户端进行"一问一答"的交互规则
 * 交互过程分为三大步
 * 1:解析请求
 * 2:处理请求
 * 3:发送响应
 */
public class ClientHandler implements Runnable{
    private Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try{
            HttpServletRequest request = new HttpServletRequest(socket);
            HttpServletResponse response = new HttpServletResponse(socket);
            DispatcherServlet servlet = DispatcherServlet.getInstance();
            servlet.service(request, response);
            response.response();
            System.out.println("一次请求处理完毕");
            System.out.println("--------------------------");

        } catch (IOException | EmptyRequestException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}