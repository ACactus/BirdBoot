package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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

    private String requestURI;
    private String queryString;
    private Map<String, String> parameters = new HashMap<>() ;

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
        parseURI();
        //测试路径:http://localhost:8088/index.html
        System.out.println("method:"+method);//method:GET
        System.out.println("uri:"+uri);//uri:/index.html
        System.out.println("protocol:"+protocol);//protocol:HTTP/1.1
    }

    private void parseURI(){
        String []data = uri.split("\\?");
        requestURI = data[0];
        if(data.length > 1){
            queryString = data[1];
            parseParameters(queryString);
        }
        System.out.println("requestURI: " + requestURI);
        System.out.println("queryString: " + queryString);
        System.out.println("parameters: " + parameters);
    }

    private void parseParameters(String line){
        try {
            line = URLDecoder.decode(line, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String []data = line.split("\\&");
        for(String entry : data){
//                String []pair = entry.split("\\=");
//                parameters.put(pair[0], pair.length > 1 ? pair[1]:"");
            String[] pair = entry.split("\\=", 2);
            parameters.put(pair[0], pair[1]);
        }
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
    private void parseContent() throws IOException {
        if(headers.containsKey("Content-Length")){
            int contentLength = Integer.parseInt(headers.get("Content-Length"));
            System.out.println("正文长度：" + contentLength);
            InputStream in = socket.getInputStream();
            byte[] contentData = new byte[contentLength];
            in.read(contentData);
            String contentType = headers.get("Content-Type");
            if("application/x-www-form-urlencoded".equals(contentType)){
                String line = new String(contentData, StandardCharsets.ISO_8859_1);
                System.out.println(line);
                parseParameters(line);
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

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public String getHeader(String key){
        return headers.get(key);
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getQueryString() {
        return queryString;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}