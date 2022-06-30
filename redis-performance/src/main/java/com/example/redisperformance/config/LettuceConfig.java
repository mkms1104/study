package com.example.redisperformance.config;

import com.example.redisperformance.nftService.NftGeneratorUtil;
import com.example.redisperformance.nftService.winnerSelectStrategy.SyncWinnerSelectorStrategy;
import com.example.redisperformance.nftService.winnerSelectStrategy.WinnerSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class LettuceConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.clusterNode("clustercfg.test-memorydb.sytn5f.memorydb.ap-northeast-2.amazonaws.com", 6379);

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("clustercfg.test-memorydb.sytn5f.memorydb.ap-northeast-2.amazonaws.com");
        redisStandaloneConfiguration.setPort(6379);

        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder().commandTimeout(Duration.ofSeconds(5)).build();

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
        lettuceConnectionFactory.setShareNativeConnection(false);

        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        var redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
        return redisTemplate;
    }
}
