//package com.cskaoyan;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//public class UserMapperImpl implements UserMapper{
//
//    static SqlSession sqlSession;
//
//    static {
//        // 需要去加载Mybatis,然后启动Mybatis,然后执行sql，获取结果
//
//        InputStream inputStream = null;
//        try {
//            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 第二步
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//
//        // 创建一个SqlSessionFactory
//        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(inputStream);
//
//        // 创建Sqlsession
//        sqlSession = sessionFactory.openSession();
//    }
//
//
//    public List<User> selectList() {
//
//        List<User> list = sqlSession.selectList("aaa.selectList");
//
//        return list;
//    }
//}
