package com.example.redisperformance.lock.nftCounterStrategy;

import com.example.redisperformance.lock.NftGeneratorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RList;

@RequiredArgsConstructor
public class AtomicWinnerSelectorStrategy implements WinnerSelector {
    private final NftGeneratorUtil nftGeneratorUtil;

    @Override
    public void winnerSelect(String userId, Long pickNumber) {
        Long publishNumber = nftGeneratorUtil.rPublishNumber().getAndIncrement();
        if(pickNumber.equals(publishNumber)) {
            RList<String> winnerList = nftGeneratorUtil.rWinnerList();
            winnerList.add(userId);
        }
    }
}
