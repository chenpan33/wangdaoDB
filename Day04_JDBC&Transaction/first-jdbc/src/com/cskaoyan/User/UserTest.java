package com.cskaoyan.User;

import com.cskaoyan.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTest {

    static Connection connection;
    static Statement statement;

    static {
        try {
            connection = JDBCUtils.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 增加数据
    public static void main(String[] args) {


//         add();

//        update("兰钊","lanlaoshizhenshuai");

        selectAll();

//        deleteUserById(1);
    }


    // 根据Id去删除
    private static void deleteUserById(Integer id) {

        try {
            // 获取statement对象
            Statement statement = connection.createStatement();

            // 通过statement对象去执行sql
            String sql = "delete from user where id = " + id;

            System.out.println(sql);

            // 执行sql 返回值是 [影响的行数]
            int i = statement.executeUpdate(sql);

            System.out.println("i:" + i);

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            // 关闭资源
            JDBCUtils.closeSource(connection,statement,null);
        }

    }

    // 查询所有
    private static void selectAll() {
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

    private static void update(String username, String password) {

        try {
            // 获取statement对象
            Statement statement = connection.createStatement();

            // 通过statement对象去执行sql
            String sql = "update user set password = '" + password + "' where username = '" + username +"'";

            System.out.println(sql);

            // 执行sql 返回值是 [影响的行数]
            int i = statement.executeUpdate(sql);

            System.out.println("i:" + i);

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            // 关闭资源
            JDBCUtils.closeSource(connection,statement,null);
        }

    }

    // 增
    public static void add(){

        try {
            // 获取statement对象
            Statement statement = connection.createStatement();

            // 通过statement对象去执行sql
            String sql = "insert into user values (null,'兰钊','cskaoyan',30)";

            // 执行sql 返回值是 [影响的行数]
            int i = statement.executeUpdate(sql);

            System.out.println("i:" + i);

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            // 关闭资源
            JDBCUtils.closeSource(connection,statement,null);
        }

    }

    // 查
}
