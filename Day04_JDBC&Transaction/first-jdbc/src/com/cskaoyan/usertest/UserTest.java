package com.cskaoyan.usertest;

import com.cskaoyan.JDBCUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTest {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

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



    // @Test 加上以后，就可以帮助我们直接执行方法
    // @Test 有要求，修饰的方法必须是public，不能是静态的，没有返回值



    // 查询所有

    @Test
    public void testSelectAll() {
        try {
            // 获取statement对象
            Statement statement = connection.createStatement();

            // 通过statement对象去执行sql
            String sql = "select * from user";

            System.out.println(sql);

            // 执行sql 返回值是 [影响的行数]
            resultSet= statement.executeQuery(sql);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                System.out.println("username:" + username);
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @AfterClass
    public static void destroy(){
        System.out.println("关闭资源...");
        JDBCUtils.closeSource(connection,statement,resultSet);
    }


    @Test
    public void update() {
        ResultSet resultSet =null;
        try {
            // 获取statement对象
            Statement statement = connection.createStatement();

            // 通过statement对象去执行sql
            String sql = "select * from user";

            System.out.println(sql);

            // 执行sql 返回值是 [影响的行数]
            resultSet= statement.executeQuery(sql);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                System.out.println("username:" + username);
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            // 关闭资源
            JDBCUtils.closeSource(connection,statement,resultSet);
        }

    }

    @Test
    public void testLogin(){

    // 数据库注入的问题
        // 因为我们的用户名和密码是由用户输入进来的，假如用户是个程序员，那么只需要把密码写成下面的格式，那么就可以登录成功了
        // 这样就有安全性的问题
        // 根本原因是由我们的sql语句是由字符串拼接而成
        Boolean ret = login("lanzhao","ajlsfhhfejfhsdfe' or '1=1");
    }


    // 登录的方法
    private Boolean login(String username, String password) {

        try {
            statement = connection.createStatement();

            String sql = "select * from user where username = '"+ username +"' and password = '"+password+"'";

            System.out.println("sql:" + sql);

            resultSet = statement.executeQuery(sql);

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
