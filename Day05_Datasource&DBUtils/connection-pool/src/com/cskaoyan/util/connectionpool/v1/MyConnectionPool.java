package com.cskaoyan.util.connectionpool.v1;


import com.cskaoyan.util.JDBCUtils;

import java.sql.Connection;
import java.util.LinkedList;

// 是我自己的一个连接池对象
// 头进尾出
public class MyConnectionPool {

    static LinkedList<Connection> connectionPool;

    // 对这个连接池进行初始化
    static {
        addCapacity(10);
    }


    // 扩容的方法
    public static void addCapacity(Integer num) {

        if (connectionPool == null) {
            // 底层数据结构是链表
            connectionPool = new LinkedList<Connection>();
        }
        // 给数据库连接池里面放连接
        for (int i = 0; i < num; i++) {

            connectionPool.addFirst(JDBCUtils.CreateConnection());
        }
    }


    // 获取连接
    public Connection getConnection(){

        if (connectionPool.size() < 5) {

            addCapacity(10);

        }

        Connection connection = connectionPool.removeLast();

        return connection;

    }



    // 释放连接
    public void releaseConnection(Connection connection){
        connectionPool.addFirst(connection);
    }
}
