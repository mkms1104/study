package com.example.demo.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class RedisConfig() {

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
//        val lettuceConnectionFactory = redisConnectionFactory as LettuceConnectionFactory
//        lettuceConnectionFactory.shareNativeConnection = true

        return RedisTemplate<String, Any>().apply {
            this.setEnableTransactionSupport(true)
            this.setConnectionFactory(redisConnectionFactory)
            this.setDefaultSerializer(StringRedisSerializer())
            this.valueSerializer = GenericToStringSerializer(Any::class.java) // Any 타입의 value 값을 string 타입으로 직렬화한다.
            this.hashValueSerializer = GenericToStringSerializer(Any::class.java)
        }
    }
}