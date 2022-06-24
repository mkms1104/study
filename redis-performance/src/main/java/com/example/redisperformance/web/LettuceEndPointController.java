package com.example.redisperformance.web;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

@RestController
@RequestMapping("lettuce-endpoint")
@RequiredArgsConstructor
public class LettuceEndPointController {
    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("ok")
    public String ok() {
        return "ok";
    }

//    @GetMapping("save")
    public String save() {
        var opsForHash = redisTemplate.opsForHash();
        opsForHash.put(createId(), "name", "mskim-" + createId());
        return "save";
    }

    @GetMapping("get")
    public String get() {
        var opsForHash = redisTemplate.<String, String>opsForHash();
        return opsForHash.get("kwd:" + createId(), "cpc");
    }

    @GetMapping("dummyInsert")
    public String dummyInsert() {
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

        return "dummyInsert";
    }

    private String createId() {
        var random = new SplittableRandom();
        return String.valueOf(random.nextInt(1, 1000000000));
    }
}
