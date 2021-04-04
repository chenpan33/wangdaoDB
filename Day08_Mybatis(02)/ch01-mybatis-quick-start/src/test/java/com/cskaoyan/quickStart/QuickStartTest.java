package com.cskaoyan.quickStart;

import com.cskaoyan.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QuickStartTest {

    static SqlSession sqlSession;

    @BeforeClass
    public static void init() throws IOException {
        // 需要去加载Mybatis,然后启动Mybatis,然后执行sql，获取结果

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 第二步
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 创建一个SqlSessionFactory
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        // 创建Sqlsession
        sqlSession = sessionFactory.openSession();


    }

    @AfterClass
    public static void destory(){

        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void test01(){

        // 需要去加载Mybatis,然后启动Mybatis,然后执行sql，获取结果

        ClassLoader classLoader = User.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("mybatis-config.xml");

        // 第二步
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 创建一个SqlSessionFactory
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        // 创建Sqlsession
        SqlSession sqlSession = sessionFactory.openSession();

        // 执行sql
        User user = (User)sqlSession.selectOne("aaa.ccc");

        System.out.println(user);

        // 关闭sqlSession
        sqlSession.close();


    }

    @Test
    public void test02() throws IOException {

        // 需要去加载Mybatis,然后启动Mybatis,然后执行sql，获取结果

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 第二步
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 创建一个SqlSessionFactory
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        // 创建Sqlsession
        SqlSession sqlSession = sessionFactory.openSession();

        // 执行sql
        User user = (User)sqlSession.selectOne("aaa.ccc");

        System.out.println(user);

        // 关闭sqlSession
        sqlSession.close();


    }

    @Test
    public void test03(){

        User user = sqlSession.selectOne("aaa.bbb");

        System.out.println(user);
    }


    // 增
    @Test
    public void test04(){

        User user = new User();
        user.setId(3);
        user.setUsername("长风");
        user.setPassword("打钱");
        user.setAge(30);

        int affectedRows = sqlSession.insert("aaa.insertUser", user);

        System.out.println("affectedRows:" + affectedRows);

        // 因为我们这里Mybatis默认事务是不提交的，我们需要手动提交事务
        sqlSession.commit();

    }

    // 删
    @Test
    public void testUserDelete() {

        int affectedRows = sqlSession.delete("aaa.deleteUserById", 2);

        System.out.println(affectedRows);

        sqlSession.commit();

    }

    // 改
    @Test
    public void testUpdateUser(){

        int affectedRows = sqlSession.update("aaa.updateUserById", "天明");

        sqlSession.commit();

        System.out.println("affectedRows:" + affectedRows);
    }


    // 查List
    @Test
    public void testSelectUserList(){

        List<User> list = sqlSession.selectList("aaa.selectList");

        System.out.println(list);


    }

}
