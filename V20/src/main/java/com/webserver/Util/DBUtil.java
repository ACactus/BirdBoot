package com.webserver.Util;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/28 14:01
 */
public class DBUtil {
    private static DruidDataSource dds;
    static {
        dds = new DruidDataSource();
    }

    private static void init(){
        dds = new DruidDataSource();
        dds.setUsername("root");
        dds.setPassword("root");
        dds.setUrl("jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
        dds.setInitialSize(5);
        dds.setMaxActive(20);
    }

    public static Connection getConnection() throws SQLException {
        return dds.getConnection();
    }

}
