package com.webserver.entity;

import java.io.Serializable;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/19 14:09
 */
public class User implements Serializable {
    private String userName;
    private String passwd;
    private String nickName;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passwd='" + passwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String userName, String passwd, String nickName, int age) {
        this.userName = userName;
        this.passwd = passwd;
        this.nickName = nickName;
        this.age = age;
    }
}
