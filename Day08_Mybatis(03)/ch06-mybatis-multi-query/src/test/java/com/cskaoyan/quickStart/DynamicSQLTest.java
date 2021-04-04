package com.cskaoyan.quickStart;

import com.cskaoyan.dto.Clazz;
import com.cskaoyan.dto.User;
import com.cskaoyan.dto.UserVO;
import com.cskaoyan.dto.multi2multi.Student;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DynamicSQLTest{

    static SqlSession sqlSession;
    static UserMapper userMapper;
    static ClazzMapper clazzMapper;
    static StudentMapper studentMapper;

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

        clazzMapper = sqlSession.getMapper(ClazzMapper.class);

        studentMapper = sqlSession.getMapper(StudentMapper.class);


    }

    @AfterClass
    public static void destory(){

        sqlSession.commit();
        sqlSession.close();

    }


    @Test
    public void testSelectUserById(){

        User user = userMapper.selectUserWithDetailById(10);

        System.out.println(user);


    }

    @Test
    public void testSelectClazzByName(){

        Clazz clazz = clazzMapper.selectClazzWithStudentsByName("一班");

        System.out.println(clazz);

    }

    @Test
    public void testSelectStudentByName(){

        Student student = studentMapper.selectStudentWithCoursesByName("张飞");

        System.out.println(student);


    }

}
