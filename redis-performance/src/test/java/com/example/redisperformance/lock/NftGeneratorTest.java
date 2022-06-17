package com.example.redisperformance.lock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.LongCodec;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class NftGeneratorTest {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private NftRepository nftRepository;

    @BeforeEach
    public void remove() {
        redissonClient.getQueue("nft").delete();
    }

    @Test
    public void load() {
        final long totalTicketCount = 100;
        RQueue<Object> nfts = redissonClient.getQueue("nft", StringCodec.INSTANCE);

        for(int i=1; i<=totalTicketCount; i++) {
            nfts.add(i);
        }

        assertEquals(100, redissonClient.getQueue("nft").size());
    }

    @Test
    public void generate() {
        RQueue<Long> nfts = redissonClient.<Long>getQueue("nft", LongCodec.INSTANCE);
        nfts.add(1L);
        nfts.add(2L);
        nfts.add(3L);

        assertEquals(1, nfts.poll());
        assertEquals(2, nfts.poll());
        assertEquals(3, nfts.poll());
        assertNull(nfts.poll());
    }
}