package com.example.persistence.myBatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

public class MemberDao {
    private final SqlSessionFactory sqlSessionFactory;

    public MemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.insert("insert", Map.of("id", 1, "name", "mskim"));
        }
    }

    public List<Member> findById() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("findById", 1);
        }
    }
}
