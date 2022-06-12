package ch02;

import ch01.DaoFactory;
import ch01.User;
import ch01.UserDao;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    private UserDao dao;

    @Before
    public void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.dao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        User user = new User();
        user.setId("whiteship");
        user.setName("김민수");
        user.setPassword("married");

        dao.add(user);

        User user2 = dao.get(user.getId());

        assertEquals(user2.getName(), user.getName());
        assertEquals(user2.getPassword(), user.getPassword());
    }

    @Test
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        dao.get("unknown_id"); // 예외 발생 지점

        assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("unknown_id"); // 예외 발생 지점
        });
    }
}
