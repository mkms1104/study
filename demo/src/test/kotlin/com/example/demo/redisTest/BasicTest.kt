package com.example.demo.redisTest

import com.example.demo.mapStruct.MessageDto
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.data.redis.core.RedisKeyValueTemplate
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.test.context.TestConstructor

@DataRedisTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class BasicTest(
    private val redisTemplate: RedisTemplate<String, String>,
    private val redisMemberRepository: RedisMemberRepository
) {

    @Test
    fun aaa() {
        //redisTemplate.stringSerializer = StringRedisSerializer()

        redisTemplate.opsForValue().set("name", "민수")
        redisTemplate.opsForValue().set("age", "12")
        //redisTemplate.opsForValue().set("obj", MessageDto("a", "b", "c", "d"))
        val data = redisTemplate.opsForValue().get("name")
        println(data)
    }

    @Test
    fun redisRepository() {
        redisMemberRepository.save(RedisMember("mskim", "김민수"))
        redisMemberRepository.save(RedisMember("mms", "민스"))

        val a = redisMemberRepository.findById("mskim")
        println(a)
    }
}