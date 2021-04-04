package com.cskaoyan.connectionpool.dbcp;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPUtils {

    static DataSource dataSource;

    static {

        try {
            // 读取文件
            InputStream in = new FileInputStream("dbcp.properties");

            Properties properties = new Properties();

            properties.load(in);

            // 创建一个DBCP的数据源工厂 （数据源就等于连接池）
            BasicDataSourceFactory dataSourceFactory = new BasicDataSourceFactory();

            // 根据数据源工厂，创建一个数据源
            dataSource = dataSourceFactory.createDataSource(properties);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
