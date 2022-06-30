package com.example.redisperformance.web;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.SplittableRandom;

@RestController
@RequestMapping("redisson-endpoint")
//@RequiredArgsConstructor
public class RedissonEndPointController {
    private final RedissonClient redissonClient = null;

    @GetMapping("get")
    public String get() {
        var rMap = redissonClient.<String, String>getMap("kwd:" + createId());
        return rMap.get("cpc");
    }

    private String createId() {
        var random = new SplittableRandom();
        return String.valueOf(random.nextInt(1, 1000000000));
    }
}
