package com.example.redisperformance.config;

import com.example.redisperformance.nftService.NftGeneratorUtil;
import com.example.redisperformance.nftService.winnerSelectStrategy.LockWinnerSelectorStrategy;
import com.example.redisperformance.nftService.winnerSelectStrategy.SyncWinnerSelectorStrategy;
import com.example.redisperformance.nftService.winnerSelectStrategy.WinnerSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        var redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
        return redisTemplate;
    }

    @Bean
    public WinnerSelector winnerSelector(NftGeneratorUtil nftGeneratorUtil) {
        return new SyncWinnerSelectorStrategy(nftGeneratorUtil);
    }
}
