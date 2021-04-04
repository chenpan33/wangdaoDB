package com.cskaoyan.connectionpool.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Utils {

    // 声明一个数据源
    static DataSource dataSource;

    static {
        // 创建数据源
        dataSource = new ComboPooledDataSource();
    }

    public static Connection getConnection(){

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }
}
