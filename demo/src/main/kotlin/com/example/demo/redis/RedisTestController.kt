package com.example.demo.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("endpoint")
class RedisTestController(
    val redisTemplate: RedisTemplate<String, Any>
) {
    @GetMapping("ok")
    fun ok(): String {
        return "ok"
    }

    @GetMapping("save")
    fun save() {
        val opsForValue = redisTemplate.opsForValue()

        val id = createId()
        opsForValue.set("user:$id:age", 30)
        opsForValue.set("user:$id:name", "mskim")
    }

    @GetMapping("get")
    fun get(): Any {
        val opsForValue = redisTemplate.opsForValue()

        val id = createId()
        return opsForValue.get("user:$id:age") ?: 0L
    }

    private fun createId(): String {
        val random = SplittableRandom()
        return random.nextInt(1, 1000000000).toString()
    }
}