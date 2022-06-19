package com.example.redisperformance.lock.nftCounterStrategy;

import com.example.redisperformance.lock.NftGeneratorUtil;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RList;

public interface WinnerSelector {
    void winnerSelect(String userId, Long pickNumber);

    default void defaultWinnerSelector(NftGeneratorUtil nftGeneratorUtil, String userId, Long pickNumber) {
        RAtomicLong rPublishNumber = nftGeneratorUtil.rPublishNumber();
        long publishNumber = rPublishNumber.get();

        if(pickNumber.equals(publishNumber)) {
            RList<String> winnerList = nftGeneratorUtil.rWinnerList();
            winnerList.add(userId);
        }

        rPublishNumber.set(publishNumber + 1);
    }
}
