package com.cskaoyan.quickStart;

import com.cskaoyan.dto.User;
import com.cskaoyan.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class ParamTest{

    static SqlSession sqlSession;
    static UserMapper userMapper;

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

        userMapper = sqlSession.getMapper(UserMapper.class);


    }

    @AfterClass
    public static void destory(){

        sqlSession.commit();
        sqlSession.close();
    }


    // 穿一个简单类型的参数（什么叫简单类型呢？Mybatis内置别名的类型就是简单类型，在Java中，包括基本类型以及java.lang.String
    // 简单类型的传参，我们的parameterType可以省略）


    @Test
    public void testSimpleParam() {

        User user = userMapper.selectUserById(3);

        System.out.println(user);

    }

    @Test
    public void testSimpleParam2() {

        User user = userMapper.selectUserByIdAndUsername(3,"长风");

        System.out.println(user);

    }

    // 插入用户
    @Test
    public void testInserUser(){
        User user = new User();
        user.setId(null);
        user.setUsername("貂蝉");
        user.setPassword("董卓");
        user.setAge(18);

        Integer affectedRows = userMapper.insertUser(user);
    }

    // 插入用户
    @Test
    public void testInserUser2(){
        User user = new User();
        user.setId(null);
        user.setUsername("张飞");
        user.setPassword("俺也一样");
        user.setAge(28);

        Integer affectedRows = userMapper.insertUser2(user);

    }

    // 通过map来传值
    @Test
    public void testSelectListByMap(){

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("id",2);
        hashMap.put("username","张飞");


        List<User> userList = userMapper.selectUserByMap(hashMap);

        System.out.println(userList);

    }

    // 通过位置来传值
    @Test
    public void testSelectUserByIndex() {

        User user = userMapper.selectUserByIdAndUsername2(3,"长风");

        System.out.println(user);

    }


    @Test
    public void testSimpleParam$() {

        User user = userMapper.selectUserById$(3);

        System.out.println(user);

    }


    @Test
    public void testUserListByTableName() {

        List<User> userList = userMapper.selectUserListByTableName("user2");

        System.out.println(userList);


    }

    @Test
    public void testSelectUserOrderBy(){

        User user = userMapper.selectUserOrderBy("age");

        System.out.println(user);

    }

}
