package com.cskaoyan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCDemo5 {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            connection= JDBCUtils.getConnection();

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

            System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeSource(connection,statement,resultSet);
        }
    }
}
