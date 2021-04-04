package com.cskaoyan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCDemo2 {

    public static void main(String[] args) {

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
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            // 注册驱动(可以省略 - SPI )
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            // 获取连接
             connection = DriverManager.getConnection(url,user,password);

            // 获取statement对象，发送sql语句
             statement = connection.createStatement();

            // 执行sql语句，获取返回结果集
             resultSet = statement.executeQuery("select * from clazz");

            // 解析返回的结果集，获取我们想要的数据
            // 这个结果集是一个类似于游标的结构

            ArrayList<Clazz> list = new ArrayList<>();

            while (resultSet.next()) {

                Clazz clazz = new Clazz();

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                clazz.setId(id);
                clazz.setName(name);

                list.add(clazz);
            }

            System.out.println("list:" + list);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
}
