package com.webserver.test;

import javax.activation.MimetypesFileTypeMap;
import java.awt.datatransfer.FlavorEvent;
import java.io.File;
import java.nio.charset.MalformedInputException;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/18 14:38
 */
public class ContentTypeDemo {
    public static void main(String[] args) {
        MimetypesFileTypeMap min = new MimetypesFileTypeMap();
        File file = new File("demo.css");
        String contentType = min.getContentType(file);
        System.out.println(contentType);
    }
}
