package com.webserver.test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/19 9:34
 */
public class SplitTest {
    public static void main(String[] args) {
        String line = "a=Ab=Bc=Cd=D==";
        System.out.println(line);
        String[] data = line.split("=", 10);
        System.out.println(Arrays.toString(data));
    }
}
