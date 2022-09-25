package com.example.jpashop.lock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class JpaLockTest {
    @Autowired LockTestRepository lockTestRepository;
    @Autowired LockTestService lockTestService;

    @Rollback(false)
    @Test
    void aaa() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> lockTestService.slowSave());
        executorService.execute(() -> lockTestService.slowSave());

        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }
    @Entity
    @Table(name = "lock_test")
    static class LockTest {
        @Id
        @GeneratedValue
        private Long id;

        @Column(unique = true)
        private String name;

        protected LockTest() {
        }
        public LockTest(String name) {
            this.name = name;
        }
    }
}
