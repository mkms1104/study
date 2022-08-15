package com.example.temp.ably.service;

import com.example.temp.ably.domain.Users;
import com.example.temp.ably.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class FavoriteDeskServiceTest {
    @Autowired FavoriteDeskService favoriteDeskService;
    @Autowired UsersRepository usersRepository;

    @BeforeEach
    public void init() {
        Users users1 = new Users("mkms1104@naver.com", "mskim", "1234");
        Users users2 = new Users("abc1104@naver.com", "abc", "1234");
        Users users3 = new Users("kkk1104@naver.com", "kkk", "1234");
        usersRepository.save(users1);
        usersRepository.save(users2);
        usersRepository.save(users3);
    }


}