package com.webserver.core;

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
        try {
            //1解析请求
            String line = readLine();
            System.out.println("请求行内容:"+line);
            //请求行相关信息
            String method;//请求方式
            String uri;//抽象路径
            String protocol;//协议版本
            //将请求行内容按照空格拆分为三部分，分别初始化三个变量
            String[] data = line.split("\\s");
            method = data[0];
            uri = data[1];
            protocol = data[2];
            //测试路径:http://localhost:8088/index.html
            System.out.println("method:"+method);//method:GET
            System.out.println("uri:"+uri);//uri:/index.html
            System.out.println("protocol:"+protocol);//protocol:HTTP/1.1


            //读取消息头
            Map<String,String> headers = new HashMap<>();
            while(true) {
                line = readLine();
                if(line.isEmpty()){//读取到了空行(消息头发送完毕了)
                    break;
                }
                System.out.println("消息头:" + line);
                //line==>Connection: keep-alive
                data = line.split(":\\s");//data:{Connection, keep-alive}
                headers.put(data[0],data[1]);
            }
            System.out.println("headers:"+headers);

            //2处理请求

            //3发送响应

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //HTTP协议要求交互完毕要断开连接
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readLine() throws IOException {
        InputStream in = socket.getInputStream();
        //实现读取一行字符串的操作,便于我们读取请求行和消息头
        int d;
        StringBuilder builder = new StringBuilder();
        char pre='a',cur='a';//pre表示上次读取的字符,cur表示本次读取的字符
        while((d = in.read())!=-1){
            cur = (char)d;//本次读取到的字符
            if(pre==13 && cur==10){//判断是否连续读取到了回车+换行
                break;
            }
            builder.append(cur);
            pre = cur;//进入下次循环前,将本次读取的字符记作上次读取的字符
        }
        return builder.toString().trim();
    }
}







