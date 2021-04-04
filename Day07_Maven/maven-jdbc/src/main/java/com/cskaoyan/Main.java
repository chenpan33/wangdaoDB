package com.cskaoyan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {


        // 老的做法
        FileInputStream fileInputStream = new FileInputStream("jdbc.properties");
        Properties properties = new Properties();

        properties.load(fileInputStream);

        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");


        // 新的做法
        // 可以通过类加载器去获取配置文件的绝对路径
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource("jdbc.properties");


        // 连接数据库

        // 注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 获取连接
        Connection connection = DriverManager.getConnection(url, username, password);

        // 获取statement
        Statement statement = connection.createStatement();

        // 获取结果集，解析
        ResultSet resultSet = statement.executeQuery("select * from user where id = 33010");

        if (resultSet.next()) {
            int id = resultSet.getInt("id");

            String username1 = resultSet.getString("username");

            System.out.println("id:"+ id + ", username:" + username1);
        }


        // 关闭资源
        connection.close();
        resultSet.close();
        statement.close();
    }
}
