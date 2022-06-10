package com.example.demo.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("redis")
class RedisTestController(
    val redisTemplate: RedisTemplate<String, Any>
) {

    @PostMapping("save")
    fun save() {
        val opsForValue = redisTemplate.opsForValue()
        opsForValue.set("name", "mskim")
    }

    @GetMapping("list")
    fun list() {
        val opsForValue = redisTemplate.opsForValue()
        opsForValue.get("name")

    }

    @GetMapping("get")
    fun get() {
        val opsForValue = redisTemplate.opsForValue()
        opsForValue.get("name")
    }
}