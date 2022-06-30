package com.example.redisperformance.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useClusterServers()
//                .addNodeAddress("redis://clustercfg.test-memorydb.sytn5f.memorydb.ap-northeast-2.amazonaws.com:6379")
//                .setTimeout(5000)
//                .setPassword("epe11h11m123!@#$%")
//                ;
//        return Redisson.create(config);
//    }
}
