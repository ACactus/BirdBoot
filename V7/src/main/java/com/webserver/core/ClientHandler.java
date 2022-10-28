package com.webserver.core;


import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

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

            File root = new File(ClientHandler.class.getClassLoader().getResource(".").toURI());
            File staticDir = new File(root,"static");

            String path = request.getUri();
            File file = new File(staticDir, path);
            String statusLine;
            if(file.exists() && !file.isDirectory()){
                response.setContentFile(new File(staticDir, path));
            }else{
                response.setStatusCode(404);
                response.setStatusReason("NotFound");
                response.setContentFile(new File(staticDir, "404.html"));
            }

            response.response();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }



}