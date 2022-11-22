package com.example.persistence.myBatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisSimpleTester {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new ClassPathResource("mybatis-config.xml").getInputStream();
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        MemberDao memberDao = new MemberDao(sqlSessionFactory);


        System.out.println(memberDao.insert());
        System.out.println(memberDao.findById());
    }
}
