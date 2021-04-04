package com.cskaoyan.quickStart;

import com.cskaoyan.dto.User;
import com.cskaoyan.mapper.ClazzMapper;
import com.cskaoyan.mapper.StudentMapper;
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

public class CacheTest {

    static SqlSession sqlSession;
    static UserMapper userMapper;
    static ClazzMapper clazzMapper;
    static StudentMapper studentMapper;
    static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() throws IOException {
        // 需要去加载Mybatis,然后启动Mybatis,然后执行sql，获取结果

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 第二步
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 创建一个SqlSessionFactory
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        // 创建Sqlsession
        sqlSession = sqlSessionFactory.openSession();

        userMapper = sqlSession.getMapper(UserMapper.class);

        clazzMapper = sqlSession.getMapper(ClazzMapper.class);

        studentMapper = sqlSession.getMapper(StudentMapper.class);


    }

//    @AfterClass
//    public static void destory(){
//
//        sqlSession.commit();
//        sqlSession.close();
//
//    }



    // 一级缓存第一种情况，基于同一个 userMapper
    @Test
    public void testSelectUserById(){

        User user1 = userMapper.selectUserById(10); // 查询数据库，并且把结果放入一级缓存
        User user2 = userMapper.selectUserById(10); // 查询一级缓存
        User user3 = userMapper.selectUserById(10); // 查询一级缓存
        User user4 = userMapper.selectUserById(10); // 查询一级缓存

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);

        sqlSession.commit();                // 清空缓存

        User user5 = userMapper.selectUserById(10);


        sqlSession.close();

    }

    //  一级缓存第二种情况， 不同的userMapper，但是是同一个sqlSession
    @Test
    public void testSelectUserById2(){

        UserMapper userMapper1 = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper3 = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper4 = sqlSession.getMapper(UserMapper.class);


        User user1 = userMapper1.selectUserById(10);   // 放入一级缓存
        User user2 = userMapper2.selectUserById(10);   // 查询一级缓存
        User user3 = userMapper3.selectUserById(10);   // 查询一级缓存
        User user4 = userMapper4.selectUserById(10);   // 查询一级缓存


        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);


        System.out.println("查询UserId为11......");
        User user = userMapper4.selectUserById(11);

    }

    //  不同的sqlSession，不会走一级缓存
    @Test
    public void testSelectUserById3(){


        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        SqlSession sqlSession4 = sqlSessionFactory.openSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        UserMapper userMapper4 = sqlSession4.getMapper(UserMapper.class);

        User user1 = userMapper1.selectUserById(10);
        User user2 = userMapper2.selectUserById(10);
        User user3 = userMapper3.selectUserById(10);
        User user4 = userMapper4.selectUserById(10);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);

    }

    // 模拟插入数据一级缓存失效的情况

    @Test
    public void testSelectUserById4(){

        User user1 = userMapper.selectUserById(10); // 查询数据库，并且把结果放入一级缓存
        User user2 = userMapper.selectUserById(10); // 查询一级缓存

        // 插入数据
        Integer affectedRows = inserUser();

        if (affectedRows > 0) {
            System.out.println("插入数据成功....");
        }else {
            return;
        }


        User user3 = userMapper.selectUserById(10); // 查询一级缓存
        User user4 = userMapper.selectUserById(10); // 查询一级缓存

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);

        sqlSession.commit();                // 清空缓存

        User user5 = userMapper.selectUserById(10);


        sqlSession.close();

    }

    private Integer inserUser() {

        User user = new User();
        user.setUsername("风华哥");
        user.setPassword("别吹水了..");
        user.setAge(35);

        Integer affectedRows = userMapper.insertUser(user);
        sqlSession.commit();

        return affectedRows;
    }


    // 测试二级缓存
    // Cache Hit Ratio [com.cskaoyan.mapper.UserMapper]: 0.0 这个表示二级缓存已经开启，但是没有命中
    @Test
    public void testSelectUserById5(){


        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        SqlSession sqlSession4 = sqlSessionFactory.openSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        UserMapper userMapper4 = sqlSession4.getMapper(UserMapper.class);

        User user1 = userMapper1.selectUserById(10); // 执行查询

        sqlSession1.commit();                       // 把结果放入二级缓存

        /**
         * Cache Hit Ratio [com.cskaoyan.mapper.UserMapper]: 0.5
         * Cache Hit Ratio [com.cskaoyan.mapper.UserMapper]: 0.6666666666666666
         * Cache Hit Ratio [com.cskaoyan.mapper.UserMapper]: 0.75
         *
         */
        User user2 = userMapper2.selectUserById(10);
        User user3 = userMapper3.selectUserById(10);
        User user4 = userMapper4.selectUserById(10);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);

    }


}
