package com.example.redisperformance.lock;

import com.example.redisperformance.lock.nftCounterStrategy.WinnerSelector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.redisson.api.RQueue;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class NftGenerator {
    private final WinnerSelector winnerSelector;
    private final NftGeneratorUtil nftGeneratorUtil;
    private final Long PICK_NUMBER = 10L;

    public void load(Long pubCnt) {
        nftGeneratorUtil.rPublishNumber().set(0);
        RQueue<String> generatedNftAddresses = nftGeneratorUtil.rNftQueue();
        for (int i = 0; i < pubCnt; i++) {
            String nftAddress = RandomStringUtils.randomAlphanumeric(7);
            generatedNftAddresses.add(nftAddress);
        }
    }

    public String generate(String userId) throws InterruptedException {
        String genNft = nftGeneratorUtil.rNftQueue().poll();
        if(Objects.isNull(genNft)) return null; // 모두 발행되었다.

        winnerSelector.winnerSelect(userId, PICK_NUMBER);
        Thread.sleep(3000L);
        return genNft;
    }

    public long remainCnt() {
        return nftGeneratorUtil.rNftQueue().size();
    }

    public long lastPublishNumber() {
        return nftGeneratorUtil.rPublishNumber().get();
    }

    public long winnerListCnt() {
        return nftGeneratorUtil.rWinnerList().size();
    }

    public void clear() {
        nftGeneratorUtil.delete(
            nftGeneratorUtil.rNftQueue(),
            nftGeneratorUtil.rPublishNumber(),
            nftGeneratorUtil.rWinnerList()
        );
    }

//    private <T extends RObject> T redissonClientImplFactory(Class<T> clazz, String key) {
//        if(clazz.isInstance(RQueue.class)) return (T) redissonClient.<String>getQueue(key, StringCodec.INSTANCE);
//        else if(clazz.isInstance(RAtomicLong.class)) return (T) redissonClient.getAtomicLong(key);
//        else if(clazz.isInstance(RList.class)) return (T) redissonClient.getList(key, StringCodec.INSTANCE);
//        else throw new IllegalArgumentException();
//    }
}
