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


    @Test
    public void testSimpleRet(){

        Integer age = userMapper.selectUserAgeById(2);

        System.out.println("age:" + age);

    }

    @Test
    public void testSelectCount(){

        Integer count = userMapper.selectCount();

        System.out.println(count);

    }

    @Test
    public void testSelectUserById(){

        UserVO user = userMapper.selectUserById2(3);

        System.out.println(user);

    }

    @Test
    public void testSelectUserNameList(){

        List<String> userNameList = userMapper.selectUserNameList();

        for (String string : userNameList) {
            System.out.println(string);
        }

    }


    @Test
    public void testSelectUserList() {

        List<User> userList = userMapper.selectUserList();

        System.out.println(userList);

    }


    @Test
    public void testSelectUserVOByResultMap(){

        UserVO userVO = userMapper.selectUserById3(3);
        System.out.println(userVO);

    }

    @Test
    public void testSelectUserVOList(){

        List<UserVO> userVOS = userMapper.selectUserVOList();


        System.out.println(userVOS);

        // 假如我们要给他转化为 user对象
        ArrayList<User> users = new ArrayList<User>();

        for (UserVO userVO : userVOS) {

            User user = new User();
            user.setAge(userVO.getAge());
            user.setPassword(userVO.getPasswd());
            user.setId(userVO.getId());
            user.setUsername(userVO.getName());

        }
    }

    @Test
    public void testLombok(){


        User user = new User();



    }


}
