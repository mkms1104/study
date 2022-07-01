package com.example.redisperformance.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        if(host.equals("localhost")) {
            config.useSingleServer().setAddress("redis://" + host + ":6379");
        }
        else {
            config.useClusterServers()
                    .addNodeAddress("rediss://" + host + ":6379")
//                    .setSslProtocols(new String[]{ "TLSv1.2" })
                    ;
        }
        return Redisson.create(config);
    }
}
