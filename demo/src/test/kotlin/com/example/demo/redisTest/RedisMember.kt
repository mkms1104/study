package com.example.demo.redisTest

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("member")
data class RedisMember(
    @Id
    val id: String,
    val name: String
) {
}