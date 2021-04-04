package com.cskaoyan.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    static Connection connection;

    static {

        try {
            // 创建properties配置文件
            Properties properties = new Properties();
            FileInputStream fileInputStream = null;
            try {
                // 读取文件
                fileInputStream = new FileInputStream("jdbc.properties");

                // 装载
                properties.load(fileInputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String password = properties.getProperty("password");
            String driverClass = properties.getProperty("driverClass");


//            // 注册驱动(可以省略 - SPI )
//            // 这种做法其实不太好，假如我们数据库的驱动要换的话，那么就需要我们来修改这个代码
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            // 这么做的好处是可以帮助我们解耦
            Class.forName(driverClass);

            // 获取连接
            connection = DriverManager.getConnection(url, user, password);
        }catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    // 获取连接对象
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        return connection;

    }


    // 关闭资源
    public static void closeSource(Connection connection,Statement statement, ResultSet resultSet){

        // 关闭资源 statement resultSet connection
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
