package com.cskaoyan;

import java.sql.*;

public class JDBCDemo1 {

    public static void main(String[] args) {



        String url = "jdbc:mysql://localhost:3306/29_back2?useSSL=false";
        String user = "root";
        String password = "123456";
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
            resultSet.next();

            String name = resultSet.getString("name");

            System.out.println("name:" + name);


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
