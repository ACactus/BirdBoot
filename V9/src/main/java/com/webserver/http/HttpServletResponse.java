package com.webserver.http;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/17 11:52
 */
public class HttpServletResponse {
    private Socket socket;
    //状态行相关信息
    private int statusCode = 200;
    private String statusReason = "OK";

    //响应头相关信息

    //响应正文
    File contentFile;

    public void response() throws IOException{
        sendStatusLine();
        sendHeaders();
        sendContent();
    }

    private void sendStatusLine() throws IOException {
        //发送状态行
        println("HTTP/1.1" + " " + statusCode + " " + statusReason);
    }
    private void sendHeaders() throws IOException {
        //发送响应头
        println("Content-Type: text/html");
        println("Content-Length: " + contentFile.length());
        println("");
    }
    private void sendContent() throws IOException {
        //发送相应正文
        FileInputStream fis = new FileInputStream(contentFile);
        byte[]buf = new byte[1024 * 10];
        int len ;
        OutputStream outputStream = socket.getOutputStream();
        while((len = fis.read(buf)) != -1){
            outputStream.write(buf, 0, len);
        }
    }
    private void println(String line) throws IOException {
        byte[] data = line.getBytes(StandardCharsets.ISO_8859_1);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(data);
        outputStream.write(13);//回车换行  /r
        outputStream.write(10);//        /n
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public void setContentFile(File contentFile) {
        this.contentFile = contentFile;
    }

    public HttpServletResponse(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public File getContentFile() {
        return contentFile;
    }
}
