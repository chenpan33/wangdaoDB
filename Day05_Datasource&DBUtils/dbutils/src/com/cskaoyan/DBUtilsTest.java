package com.cskaoyan;

import com.cskaoyan.utils.DruidUtils;
import com.mysql.jdbc.Driver;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtilsTest {


    // DBUtils
    public void testDBUtils(){


        //提供了一些简单的方法，帮助我们去关闭资源,提交事务
//        DbUtils.close();
//        DbUtils.closeQuietly();

//        DbUtils.commitAndClose();
//        DbUtils.commitAndCloseQuietly();

    }

    // QueryRunner
    // 传数据源
    @Test
    public void testQueryRunnerWithDatasource() throws SQLException {

        // 获取到数据源
        DataSource dataSource = DruidUtils.getDataSource();

        QueryRunner queryRunner = new QueryRunner(dataSource);

        User user = queryRunner.query("select * from user where id = 33029", new MyResultSetHandler());

        System.out.println(user);

    }


    //不传数据源、直接传connection
    @Test
    public void testQueryRunnerWithOutDatasource() throws SQLException {


        QueryRunner queryRunner = new QueryRunner();

        Connection connection = DruidUtils.getConnection();

        User user = queryRunner.query(connection,"select * from user where id = 33029", new MyResultSetHandler());

        System.out.println(user);

        connection.close();

    }

    // queryRunner.query() 传数据源  不传数据源
    @Test
    public void testQueryRunner4Query() throws SQLException {

        // 创建一个queryRunner
        QueryRunner queryRunner = new QueryRunner();

        // 获取连接
        Connection connection = DruidUtils.getConnection();

        // 执行sql
        String sql = "select * from user where id = ?";
        User user = queryRunner.query(connection, sql, new MyResultSetHandler(), 33209);

        System.out.println("user:" + user);

        connection.close();


    }

    // queryRunner.query() 传数据源  不传数据源
    @Test
    public void testQueryRunner4Query2() throws SQLException {

        // 创建一个queryRunner
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

        // 执行sql
        String sql = "select * from user where id = ?";
        User user = queryRunner.query( sql, new MyResultSetHandler(), 33209);

        System.out.println("user:" + user);


    }

    // queryRunner.update()
    @Test
    public void testQueryRunnerUpdate() throws SQLException {

        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

        String sql = "update user set username = ? where id = ?";
        int affectedRows = queryRunner.update(sql,"景天",33209);

        System.out.println("affectedRows:" +affectedRows);
    }

    // queryRunner.update()
    @Test
    public void testQueryRunnerUpdate2() throws SQLException {

        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DruidUtils.getConnection();

        String sql = "update user set username = ? where id = ?";
        int affectedRows = queryRunner.update(connection,sql,"长风",33209);

        System.out.println("affectedRows:" +affectedRows);
    }


    // BeanHandler 可以帮助我们去封装单个bean
    @Test
    public void testBeanHandler() throws SQLException {

        // 创建QueryRunner
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

        // 执行sql
        String sql = "select * from user where id = ?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), 33209);

        System.out.println(user);

    }

    // BeanListHandler 可以帮助我们把结果封装为一个BeanList
    @Test
    public void testBeanListHandler() throws SQLException {

        // 创建QueryRunner
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

        // 执行sql
        String sql = "select * from user where id in (?,?,?)";
        // 注意：直接传对象
        BeanListHandler<User> beanListHandler = new BeanListHandler<User>(User.class);
        List<User> userList = queryRunner.query(sql, beanListHandler,33209,33210,33211);

        System.out.println(userList);

    }

    // ColumnListHandler 直接把一列的数据封装到一个List里面去
    @Test
    public void testColumnListHandler() throws SQLException {
        // 创建QueryRunner
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

        // 执行sql
        String sql = "select username from user where id in (?,?,?)";
        // 注意：直接传对象
        ColumnListHandler<String> columnListHandler = new ColumnListHandler<String>();
        List<String> usernameList = queryRunner.query(sql, columnListHandler,33209,33210,33211);

        for (String s : usernameList) {
            System.out.println("username:" + s);
        }

    }

    // ScalarHandler 这个是查一个数值的时候用的
    @Test
    public void testScarlarHandler() throws SQLException {
        // 创建QueryRunner
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

        // 执行sql
        String sql = "select count(1) from user where id in (?,?,?)";

        ScalarHandler<Long> scalarHandler = new ScalarHandler<Long>();

        Long rows  = queryRunner.query(sql, scalarHandler,33209,33210,33211);

        System.out.println("行数：" + rows);

    }

}




// 自己实现了一个ResultSetHandler接口
class MyResultSetHandler implements ResultSetHandler<User>{

    public User handle(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (resultSet.next()) {

            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            int age = resultSet.getInt("age");

            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setAge(age);
        }
        return user;
    }
}
