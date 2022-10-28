package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/17 9:20
 */
public class HttpServletRequest {

    private Socket socket;

    //请求行相关信息
    private String method;
    private String uri;
    private String protocol;

    //读取消息头
    private HashMap<String, String> headers = new HashMap<>();

    public HttpServletRequest(Socket socket) throws IOException, EmptyRequestException {
        this.socket = socket;
        parseRequestLine();
        parseHeaders();
        parseContent();
    }

    //解析请求行
    private void parseRequestLine() throws IOException, EmptyRequestException {
        String line = readLine();
        System.out.println("请求行内容:"+line);
        if(line.isEmpty()) throw new EmptyRequestException();
        //将请求行内容按照空格拆分为三部分，分别初始化三个变量
        String[] data = line.split("\\s");
        method = data[0];
        uri = data[1];
        protocol = data[2];
        //测试路径:http://localhost:8088/index.html
        System.out.println("method:"+method);//method:GET
        System.out.println("uri:"+uri);//uri:/index.html
        System.out.println("protocol:"+protocol);//protocol:HTTP/1.1
    }
    //解析消息头
    private void parseHeaders() throws IOException {
        while(true) {
            String line = readLine();
            if(line.isEmpty()){//读取到了空行(消息头发送完毕了)
                break;
            }
            System.out.println("消息头:" + line);
            //line==>Connection: keep-alive
            String []data = line.split(":\\s");//data:{Connection, keep-alive}
            headers.put(data[0],data[1]);
        }
    }
    //解析消息正文
    private void parseContent(){

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

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }


    public String  getHeaders(String name) {
        return headers.get(name);
    }
}