package com.example.redisperformance.config;

import com.example.redisperformance.nftService.NftGeneratorUtil;
import com.example.redisperformance.nftService.winnerSelectStrategy.SyncWinnerSelectorStrategy;
import com.example.redisperformance.nftService.winnerSelectStrategy.WinnerSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanConfig {

    @Bean
    public WinnerSelector winnerSelector(NftGeneratorUtil nftGeneratorUtil) {
        return new SyncWinnerSelectorStrategy(nftGeneratorUtil);
    }
}
