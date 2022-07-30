package com.tobi;

import com.tobi.ch03.jdbcTemplate.UserDao;
import com.tobi.ch05.DefaultUserLevelUpgrade;
import com.tobi.ch05.Level;
import com.tobi.ch06.UserService;
import com.tobi.ch06.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private List<User> users = Arrays.asList(
            new User("aaa", "민수1", "p1", Level.BASIC, DefaultUserLevelUpgrade.MIN_LOGCOUNT_FOR_SILVER-1, 0),
            new User("bbb", "민수2", "p2", Level.BASIC, DefaultUserLevelUpgrade.MIN_LOGCOUNT_FOR_SILVER, 0),
            new User("ccc", "민수3", "p3", Level.SILVER, 60, DefaultUserLevelUpgrade.MIN_RECCOMEND_FOR_GOLD-1),
            new User("ddd", "민수4", "p4", Level.SILVER, 60, DefaultUserLevelUpgrade.MIN_RECCOMEND_FOR_GOLD),
            new User("eee", "민수5", "p5", Level.GOLD, 100, Integer.MAX_VALUE)
    );

    @Test
    public void bean() {
        assertNotNull(userService);
        assertNotNull(userDao);

        Collections.unmodifiableCollection(List.of());
    }

    @BeforeEach
    public void setUp() {
        userDao.deleteAll();
    }

    @Test
    public void upgradeLevels() throws Exception {
        for(User user : users) userDao.add(user);

        userService.upgradeLevels();

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);
    }

    @Test
    @DisplayName("ddd id 유저의 레벨 업그레이드 도중 예외가 발생했다면 이전 유저의 업그레이드 작업은 롤백되어야 한다.")
    public void upgradeAllOrNoting() {
        // test 전용 빈으로 수동 DI
        TestDefaultUserLevelUpgrade testDefaultUserLevelUpgrade = new TestDefaultUserLevelUpgrade(users.get(3).getId());
        testDefaultUserLevelUpgrade.setUserDao(userDao);

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.setUserLevelUpgradedPolicy(testDefaultUserLevelUpgrade);
        userServiceImpl.setUserDao(userDao);

//        UserServiceTx userService = new UserServiceTx();
//        userService.setTransactionManager(transactionManager);
//        userService.setUserService(userServiceImpl);

//        TransactionHandler transactionHandler = new TransactionHandler();
//        transactionHandler.setTarget(userServiceImpl);
//        transactionHandler.setTransactionManager(transactionManager);
//        transactionHandler.setPattern("upgradeLevels");
//        transactionHandler.setPattern("1234upgradeLevels");

//        UserService userService = (UserService) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { UserService.class }, transactionHandler);

        ProxyFactoryBean proxyFactoryBean = context.getBean("&userService", ProxyFactoryBean.class);
        proxyFactoryBean.setTarget(userServiceImpl);
        UserService userService = (UserService) proxyFactoryBean.getObject();

        // given
        for(User user : users) userDao.add(user);

        // when
        try {
            userService.upgradeLevels();
//            fail("RuntimeException expected");
        } catch (TestUserLevelUpgradeException e) {
            System.out.println("레벨 업그레이드 도중 예외 발생");
        } catch (Exception e) {
            System.out.println("트랜잭션 예외 발생");
        }

        // then
        User user1get = userDao.get(users.get(1).getId());
        assertEquals(Level.BASIC, user1get.getLevel()); // bbb id 유저는 SILVER 레벨로 업그레이드 되었지만 예외가 발생해서 BRONZE 레벨로 롤백되어야 한다.
    }

    @Test
    public void add() {
        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertEquals(userWithLevelRead.getLevel(), userWithLevel.getLevel());
        assertEquals(userWithoutLevelRead.getLevel(), Level.BASIC);
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());
        if(upgraded) {
            assertEquals(userUpdate.getLevel(), user.getLevel().nextLevel());
        }
        else {
            assertEquals(userUpdate.getLevel(), user.getLevel());
        }
    }


    static class TestDefaultUserLevelUpgrade extends DefaultUserLevelUpgrade {
        private String id;

        public TestDefaultUserLevelUpgrade(String id) {
            this.id = id;
        }

        @Override
        public void upgradeLevel(User user) {
            if(user.getId().equals(this.id)) throw new TestUserLevelUpgradeException();
            super.upgradeLevel(user);
        }
    }

    static class TestUserLevelUpgradeException extends RuntimeException {

    }
}
