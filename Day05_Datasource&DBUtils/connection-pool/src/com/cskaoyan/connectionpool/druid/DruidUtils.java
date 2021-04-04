package com.cskaoyan.connectionpool.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtils {

    static DataSource dataSource;

    static {
        try {
            // 加载配置
            FileInputStream in = new FileInputStream("druid.properties");
            Properties properties = new Properties();
            properties.load(in);

            // 创建数据源工厂
            DruidDataSourceFactory druidDataSourceFactory = new DruidDataSourceFactory();

            // 创建数据源
            dataSource = druidDataSourceFactory.createDataSource(properties);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConnection(){

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }
}
