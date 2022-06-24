package com.example.redisperformance.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

@SpringBootTest
public class LettuceTest {
    @Autowired private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void pipeline() {
        List<Object> results = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0; i < 1000000; i++) {
                    var key = ("kwd:" + createId()).getBytes();
                    var hashes = Map.of(
                                                    "actYn".getBytes(), "true".getBytes(),
                                                    "cpc".getBytes(), "100".getBytes()
                                                    );
                    connection.hMSet(key, hashes);
                }
                return null;
            }
        });
    }

    private String createId() {
        var random = new SplittableRandom();
        return String.valueOf(random.nextInt(1, 1000000000));
    }
}
