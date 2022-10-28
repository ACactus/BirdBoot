package com.webserver.core;


import com.webserver.http.HttpServletRequest;

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

            File root = new File(ClientHandler.class.getClassLoader().getResource(".").toURI());
            File staticDir = new File(root,"static");

            String path = request.getUri();
            File file = new File(staticDir, path);
            String statusLine;
            if(file.exists() && !file.isDirectory()){
                statusLine = "HTTP/1.1 404 NotFound";
                file = new File(staticDir, path);
            }else{
                statusLine = "HTTP/1.1 200 OK";
                file = new File(staticDir, "404.html");
            }

            //发送状态行
            println(statusLine);
            //发送响应头
            println("Content-Type: text/html");
            println("Content-Length: " + file.length());
            println("");

            //发送相应正文
            FileInputStream fis = new FileInputStream(file);
            byte[]buf = new byte[1024 * 10];
            int len ;
            OutputStream outputStream = socket.getOutputStream();
            while((len = fis.read(buf)) != -1){
                outputStream.write(buf, 0, len);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void println(String line) throws IOException {
        byte[] data = line.getBytes(StandardCharsets.ISO_8859_1);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(data);
        outputStream.write(13);//回车换行
        outputStream.write(10);
    }

}