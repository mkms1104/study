package com.example.redisperformance.nftService.winnerSelectStrategy;

import com.example.redisperformance.nftService.NftGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RList;

@RequiredArgsConstructor
public class AtomicWinnerSelectorStrategy implements WinnerSelector {
    private final NftGeneratorUtil nftGeneratorUtil;

    @Override
    public void winnerSelect(String userId) {
        Long publishNumber = nftGeneratorUtil.rPublishNumber().getAndIncrement();
        if(PICK_NUMBER.equals(publishNumber)) {
            RList<String> winnerList = nftGeneratorUtil.rWinnerList();
            winnerList.add(userId);
        }
    }
}
