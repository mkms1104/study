package com.example.redisperformance.lock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class SimpleNftGenerateMachineTest {

    @Autowired
    private NftGenerator nftGenerator;

    @Test
    public void clear() {
        nftGenerator.clear("firstNft");
    }

    @Test
    @Order(1)
    @DisplayName("관리자는 100개의 NFT 발행한다.")
    public void step01() {
        final long totalNftCnt = 100;
        final String nftName = "firstNft";
        nftGenerator.load(nftName, totalNftCnt);

        Assertions.assertEquals(totalNftCnt, nftGenerator.remainCnt(nftName));
    }

    @Test
    @Order(2)
    @DisplayName("1000명의 사용자가 동시에 민팅을 시작한다.")
    public void step02() throws InterruptedException {
        final int userCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        CountDownLatch latch = new CountDownLatch(userCount);

        List<Callable<String>> collect = Stream.generate(this::task).limit(userCount).collect(Collectors.toList());
        List<Future<String>> futures = executorService.invokeAll(collect);

    }

    private Callable<String> task() {
        return () -> nftGenerator.generate("firstNft");
    }
}
