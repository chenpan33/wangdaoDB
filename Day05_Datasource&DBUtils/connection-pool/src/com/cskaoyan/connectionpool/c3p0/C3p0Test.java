package com.cskaoyan.connectionpool.c3p0;

import com.cskaoyan.User;
import com.cskaoyan.connectionpool.dbcp.DBCPUtils;
import com.cskaoyan.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class C3p0Test {

    @Test
    public void testSelectUserById(){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            // 根据DBCP数据库连接池获取数据源
            connection = C3p0Utils.getConnection();

            // 我们去操作数据库
            preparedStatement = connection.prepareStatement("select * from user where id = ?");

            preparedStatement.setInt(1, 9992333);

            resultSet = preparedStatement.executeQuery();

            User user = new User();

            if (resultSet.next()) {

                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setAge(resultSet.getInt("age"));
            }

            System.out.println("user:" + user);


        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            // 关闭资源怎么做呢？
            // 关闭资源可以这么做吗？
            // 我们这个地方，去调用我们connection.close()方法，其实取决于我们这个connection实例对象的close()是怎么写的？
            // 我们作为使用者，在使用完成之后，要求调用我们的connection.close()方法，尽管这个方法可能不是真正的关闭连接，至于到底
            // 这个连接关闭了没有，取决于我们数据源实现类本身的策略。
            JDBCUtils.closeSource(connection,preparedStatement,resultSet);
        }



    }
}
