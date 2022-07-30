package com.tobi;

import com.tobi.ch03.jdbcTemplate.UserDao;
import com.tobi.ch05.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
        userDao.deleteAll();
        this.user1 = new User("aaa", "민수1", "1234", Level.BASIC, 1, 0);
        this.user2 = new User("bbb", "민수2", "1234", Level.SILVER, 55, 10);
        this.user3 = new User("ccc", "민수3", "1234", Level.GOLD, 100, 40);
    }

    @Test
    public void addAndGet() {
        userDao.add(this.user1);
        userDao.add(this.user2);

        User userget1 = userDao.get(user1.getId());
        checkSameUser(userget1, user1);

        User userget2 = userDao.get(user2.getId());
        checkSameUser(userget2, user2);
    }

    @Test
    public void update() {
        userDao.add(user1);

        user1.setName("민수킴");
        user1.setPassword("123456");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        userDao.update(user1);

        User user1update = userDao.get(user1.getId());
        checkSameUser(user1, user1update);
    }

    private void checkSameUser(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getName(), user2.getName());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getLevel(), user2.getLevel());
        assertEquals(user1.getLogin(), user2.getLogin());
        assertEquals(user1.getRecommend(), user2.getRecommend());
    }
}