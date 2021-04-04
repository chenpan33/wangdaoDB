package com.cskaoyan.usertest;

import com.cskaoyan.JDBCUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

public class PrePareStatementTest {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static PreparedStatement preparedStatement;

    @BeforeClass
    public static void init(){
        try {
            System.out.println("初始化连接...");
            connection = JDBCUtils.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void destroy(){
        System.out.println("关闭资源...");
        JDBCUtils.closeSource(connection,preparedStatement,resultSet);
    }


    @Test
    public void test01(){

        try {
            String sql = "select * from user where id = ? and username = ?";
            // 创建prepareStatement对象
             preparedStatement = connection.prepareStatement(sql);

            // 给sql语句传值
            preparedStatement.setInt(1,1);
            preparedStatement.setString(2,"lanzhao");

            // 执行
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");

                System.out.println("id:" + id + ", age:" + age);
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testLogin(){

//        login("lanzhao","cskaoyan");

        // 模拟数据库注入
        login("lanzhao","ajlsfhhfejfhsdfe' or '1=1");


    }


    // 登录的方法
    private Boolean login(String username, String password) {

        try {
            preparedStatement = connection.prepareStatement("select * from user where username = ? and password = ?");

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("登录成功...");
                return true;
            }else {
                System.out.println("没有找到用户...");
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
