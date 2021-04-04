package com.cskaoyan;

import com.cskaoyan.util.JDBCUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

public class TransferTest {

    static Connection connection;
//    static Statement statement;
    static ResultSet resultSet;
    static PreparedStatement preparedStatement;


    @Test
    public void testTransfer(){

        transfer("风华哥","天明",10000);

    }

    // 转账方法
    private void transfer(String fromName, String toName, int money) {
        try {
            // 设置事务为不自动提交 = 开启事务
            connection.setAutoCommit(false);

        // 扣钱
            String sql = "update account set money = money + ? where name = ?";
            preparedStatement = connection.prepareStatement(sql);

            // 传参
            preparedStatement.setInt(1,-money);
            preparedStatement.setString(2,fromName);

            // 执行扣钱
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("扣减工资失败！");
                return;
            }

            int i = 1/0;

            //加钱
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, toName);

            int affectedRows2 = preparedStatement.executeUpdate();

            if (affectedRows2 == 0) {
                throw new RuntimeException("加钱失败");
            }

            connection.commit();

        }catch (Exception ex) {
            // 打印异常栈信息
            ex.printStackTrace();

            // 回滚
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @BeforeClass
    public static void init(){
        try {
            connection = JDBCUtils.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public static void destory(){

        JDBCUtils.closeSource(connection,preparedStatement,resultSet);
    }
}
