package com.webserver.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/19 15:37
 */
public class URLDecodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String line = "/regUser?userName=%E5%B0%9A%E8%BF%9B&passwd=123&nickName=jjj&age=12";
        line = URLDecoder.decode(line, "UTF-8");
        System.out.println(line);
    }
}
