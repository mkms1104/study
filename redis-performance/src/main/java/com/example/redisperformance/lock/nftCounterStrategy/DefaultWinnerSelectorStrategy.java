package com.example.redisperformance.lock.nftCounterStrategy;

import com.example.redisperformance.lock.NftGeneratorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DefaultWinnerSelectorStrategy implements WinnerSelector {
    private final NftGeneratorUtil nftGeneratorUtil;

    @Override
    public void winnerSelect(String userId, Long pickNumber) {
        defaultWinnerSelector(nftGeneratorUtil, userId, pickNumber);
    }
}
