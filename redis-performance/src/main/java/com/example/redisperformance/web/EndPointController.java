package com.example.redisperformance.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.SplittableRandom;

@RestController
@RequestMapping("endpoint")
//@RequiredArgsConstructor
public class EndPointController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("ok")
    public String ok() {
        return "ok";
    }

    @GetMapping("save")
    public String save() {
        var opsForValue = redisTemplate.opsForValue();

        var id = createId();
        opsForValue.set("user:" + id + ":age", "30");
        opsForValue.set("user:" + id + ":name", "mskim");

        return "save";
    }

    @GetMapping("get")
    public String get() {
        var opsForValue = redisTemplate.opsForValue();
        var id = createId();

        String age = opsForValue.get("user:" + id + ":age");
        return age == null ? "0" : age;
    }

    private String createId() {
        var random = new SplittableRandom();
        return String.valueOf(random.nextInt(1, 1000000000));
    }
}
