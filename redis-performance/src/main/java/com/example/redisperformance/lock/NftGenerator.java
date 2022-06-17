package com.example.redisperformance.lock;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NftGenerator {
    private final RedissonClient redissonClient;

    public void load(String nftName, Long pubCnt) {
        RQueue<Object> nftQueue = redissonClient.getQueue(nftName, StringCodec.INSTANCE);
        for(int i=1; i<=pubCnt; i++) nftQueue.add(i);
    }

    public String generate(String nftName) {
        return redissonClient.<String>getQueue(nftName, StringCodec.INSTANCE).poll();
    }

    public long remainCnt(String nftName) {
        return redissonClient.getQueue(nftName, StringCodec.INSTANCE).size();
    }

    public void clear(String nftName) {
        redissonClient.getQueue(nftName).delete();
    }
}
