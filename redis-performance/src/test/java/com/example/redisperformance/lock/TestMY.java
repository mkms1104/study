package com.example.redisperformance.lock;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@DataRedisTest
public class TestMY {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @BeforeEach
    public void deleteAll() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    @DisplayName("")
    public void aaa() throws InterruptedException  {
        int numberOfThreads = 300;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                redisTemplate.opsForValue().increment("count");
                latch.countDown();
            });
        }

        latch.await();
        assertEquals(String.valueOf(numberOfThreads), redisTemplate.opsForValue().get("count"));
    }

    @Test
    public void bbb() throws InterruptedException {
        int numberOfThreads = 300;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {

            service.execute(() -> {
                redisTemplate.execute(new SessionCallback<Object>() {
                    @Override
                    public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                        operations.watch((K) "count");
                        operations.multi();
                        redisTemplate.opsForValue().increment("count");
                        return operations.exec();
                    }
                });

                latch.countDown();
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        latch.await();
//        assertEquals(String.valueOf(numberOfThreads), redisTemplate.opsForValue().get("count"));
    }

    @Test
    @DisplayName("lettuce optimistic Lock 동작 테스트")
    public void opLock() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<?> future1 = service.submit(() -> {
            redisTemplate.execute(new SessionCallback<Object>() {
                @SneakyThrows
                @Override
                public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                    operations.watch((K) "count");
                    operations.multi();
                    redisTemplate.opsForValue().set("count", "0");
                    Thread.sleep(3000L);

                    return operations.exec();
                }
            });
        });

        Future<?> future2 = service.submit(() -> {
            redisTemplate.opsForValue().set("count", "100");
        });

        future1.get();

    }
}
