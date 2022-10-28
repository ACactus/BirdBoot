package com.webserver.test;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/18 9:41
 */
public class Singleton {
    private static Singleton singleton = new Singleton();
    
    private Singleton(){}

    public Singleton getInstance(){
        return singleton;
    }
}
