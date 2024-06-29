package com.stock.util;





import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

//sqlSessionFactory -> sqlSession
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    private static Connection connection;
    public static SqlSession sqlSession =null;
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = MybatisUtils.getSqlSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public static Connection getConnection() {
        return sqlSessionFactory.openSession().getConnection();
    }
}

