package com.cskaoyan.quickStart;

import com.cskaoyan.dto.User;
import com.cskaoyan.dto.UserVO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DynamicSQLTest{

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


    @Test
    public void testSelectUserByIdAndUsername(){

        User user = userMapper.selectUserByIdAndUserName(3, "长风");

        System.out.println(user);


    }

    @Test
    public void testSelectByAge(){

        List<User> userList = userMapper.selectUserLisyByAge(30);

        System.out.println(userList);


    }

    @Test
    public void testSelectUserBySelf(){

        User user = new User();

//        user.setId(2);
//        user.setAge(18);


        List<User> userList = userMapper.selectUserBySelf(user);

        System.out.println(userList);

    }


    @Test
    public void testSelectByAge2(){

        List<User> userList = userMapper.selectUserLisyByAge2(15);

        System.out.println(userList);


    }


    @Test
    public void testUpdateUserById(){


        User user = new User();
        user.setId(2);
        user.setUsername("李鬼");

        Integer affectedRows = userMapper.updateUserBySelf(user);

        System.out.println("影响的行数:" + affectedRows);


    }


    @Test
    public void testUpdateUserById2() {


        User user = new User();
        user.setId(2);
        user.setUsername("李逵");

        Integer affectedRows = userMapper.updateUserByIdWithSet(user);

        System.out.println("影响的行数:" + affectedRows);


    }

    @Test
    public void testSelectById(){

        User user = userMapper.selectUserById(2);

        System.out.println(user);

    }

    @Test
    public void testInsertUser(){

        User user = new User();
        user.setId(6);
        user.setUsername("雷总");
        user.setPassword("are you ok");
        user.setAge(52);

        User user1 = new User();
        user1.setId(7);
        user1.setUsername("孙宇晨");
        user1.setPassword("巴菲特，吃饭吗");
        user1.setAge(30);

        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user1);

        Integer integer = userMapper.insertUserList(users);

        System.out.println("影响的行数：" + integer);


    }


    @Test
    public void testInsertUser2(){

        User user = new User();
        user.setId(8);
        user.setUsername("罗永浩");
        user.setPassword("给我来一个中杯");
        user.setAge(40);


        User user1 = new User();
        user1.setId(9);
        user1.setUsername("马保国");
        user1.setPassword("我大意了");
        user1.setAge(60);

        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user1);

        Integer integer = userMapper.insertUserList2(users);

        System.out.println("影响的行数：" + integer);


    }


    @Test
    public void testInsertUser3(){

        User user = new User();
        user.setId(12);
        user.setUsername("罗永浩");
        user.setPassword("给我来一个中杯");
        user.setAge(40);


        User user1 = new User();
        user1.setId(13);
        user1.setUsername("马保国");
        user1.setPassword("我大意了");
        user1.setAge(60);

        User[] users = new User[2];
        users[0] = user;
        users[1] =user1;

        Integer integer = userMapper.insertUserList4(users);

        System.out.println("影响的行数：" + integer);


    }

    @Test
    public void testSelectUserListByIdList(){

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) {
            list.add(i+1);
        }
        List<User> userList = userMapper.selectUserListByIdList(list);

        System.out.println(userList);


    }


    @Test
    public void testInsertSingleUser(){


        User user = new User();
        user.setId(null);
        user.setUsername("兰钊");
        user.setPassword("晚上留下来加班");
        user.setAge(40);

        Integer affectedRows = userMapper.insertUser(user);
//
//        System.out.println("affectedRows:" + affectedRows);
//
//        // 假如我们需要获取兰钊的id，那么这个时候是不是应该再查一次
//        List<User> userList = userMapper.selectUserBySelf(user);
//        if (userList.size() > 0) {
//            User user1 = userList.get(0);
//            System.out.println("获取到的用户id:" + user1.getId());
//        }

        System.out.println("获取到的用户id:" + user.getId());

    }

    @Test
    public void testInsertUserWithUseGeneratedKeys() {

        User user = new User();
        user.setId(null);
        user.setUsername("天明");
        user.setPassword("程心，你等我");
        user.setAge(20);

        Integer affectedRows = userMapper.insertUser2(user);

        System.out.println("affectedRows:" + affectedRows);

        System.out.println("id:" + user.getId());

    }







}
