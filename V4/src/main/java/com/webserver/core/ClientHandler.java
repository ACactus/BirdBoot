package main.java.com.webserver.core;

import main.java.com.webserver.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            String path = request.getUri();
            System.out.println(path);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}







