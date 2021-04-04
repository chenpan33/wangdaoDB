package com.cskaoyan.quickStart;

import com.cskaoyan.User;
import com.cskaoyan.UserMapper;
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

public class SettingTest {

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


//    // 查List
//    @Test
//    public void testSelectUserList(){
//
//        UserMapperImpl userMapper = new UserMapperImpl();
//
//        List<User> userList = userMapper.selectList();
//
//        System.out.println(userList);
//
//
//    }


    // 我们的Mybatis要求我们在使用反向代理的时候，要求我们的UserMapper.xml的namespace 必须和接口的全限定名称保持一致

    @Test
    public void testProxy(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = mapper.selectList();

        System.out.println(userList);

    }


}
