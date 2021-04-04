package com.cskaoyan.utils.statement;

import com.cskaoyan.utils.JDBCUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

public class StatementBatch {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    @BeforeClass
    public static void init(){

        try {
            connection = JDBCUtils.getConnection();
            System.out.println("connection inited ...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void destory(){

        JDBCUtils.closeSource(connection,statement,resultSet);
        System.out.println("source release...");
    }

    // 批量操作
    @Test
    public void testInsertUserBatch(){

        try {
            // 获取statement对象
            statement = connection.createStatement();

            // 添加sql语句
            // 问题：这个地方和我们的Mysql服务器通信了几次？ 1次

            // 都是在封装我们去请求Mysql的一个请求参数
            statement.addBatch("update user set username = '张飞' where id = 1");
            statement.addBatch("update user set username = '关羽' where id = 2");

            // 执行请求，和Mysql服务器之间进行通信
            int[] arr = statement.executeBatch();

            for (int i : arr) {
                System.out.println("i:" + i);
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // 批量插入  1156ms  8328 毫秒    第三次：7360毫秒
    @Test
    public void testInsertBatch(){

        long startTime = System.currentTimeMillis();
        try {
            // 创建statement对象
            statement = connection.createStatement();

            // 执行批量插入====>其实是去封装sql
            for (int i = 0; i < 10000; i++) {

                Integer age = i;
                String sql = "insert into user values (null,'天明','加钱',"+age+")";
                statement.addBatch(sql);
            }
            statement.executeBatch();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("执行一共花费了："  + (endTime-startTime) + " 毫秒");

    }
    // 1046 毫秒  7965 毫秒   第三次：8087 毫秒
    @Test
    public void normalBatch(){
        long startTime = System.currentTimeMillis();

        try {
            statement = connection.createStatement();

            for (int i = 0; i < 10000; i++) {
                String sql = "insert into user values (null,'天明','加钱',"+i+")";
                statement.executeUpdate(sql);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("执行一共花费了："  + (endTime-startTime) + " 毫秒");
    }


    // 1048 毫秒  7840 毫秒  第三次：142 毫秒
    @Test
    public void testPrepareStatementBatch(){

        long startTime = System.currentTimeMillis();
        String sql = "insert into user values (null,?,?,?)";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < 10000; i++) {
                preparedStatement.setString(1,"长风");
                preparedStatement.setString(2,"打钱");
                preparedStatement.setInt(3,i);

                // 添加到批处理中
                preparedStatement.addBatch();
                // 清空参数，方便下一次循环使用
                preparedStatement.clearParameters();
            }

            preparedStatement.executeBatch();

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("执行一共花费了："  + (endTime-startTime) + " 毫秒");
    }

    // 对比了上面三种批处理方式，我们发现三种差距不大
    /**
     *     和数据库的交互时间   = 数据库通信的时间 + 执行sql的时间
     *     第一种                      1               10000
     *     第二种                      10000           10000
     *     第三种                      1               10000
      */
    // 说明的问题：和数据库通信的时间占用比例比较小，执行sql的时间要长很多


    // 添加这个参数以后： rewriteBatchedStatements=true
    // 使用预编译的批处理速度是最快的。
    /**
     *     和数据库的交互时间   = 数据库通信的时间 +     编译sql的次数  +执行sql的次数
     *     第一种                      1               10000             10000
     *     第二种                      10000           10000             10000
     *     第三种                      1               1                 1
     */

    // 结果：我们以后进行批处理 多采用预编译的方法
}
