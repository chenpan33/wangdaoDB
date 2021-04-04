package com.cskaoyan;

import com.cskaoyan.connectionpool.MyConnectionPool;
import com.cskaoyan.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTest {

    @Test
    public void testSelectUserById() throws SQLException {

        // 获取数据库连接池
        MyConnectionPool myConnectionPool = new MyConnectionPool();

        // 通过这个连接池获取连接
        Connection connection = myConnectionPool.getConnection();

        // 我们去操作数据库
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");

        preparedStatement.setInt(1,9992333);

        ResultSet resultSet = preparedStatement.executeQuery();

        User user = new User();

        if (resultSet.next()) {

            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setAge(resultSet.getInt("age"));
       }

        System.out.println("user:" + user);

        // 释放资源
        JDBCUtils.closeSource( null, preparedStatement,resultSet);

        // 放回连接
        myConnectionPool.releaseConnection(connection);


    }
}
