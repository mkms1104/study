package com.example.redisperformance.lock.nftCounterStrategy;

import com.example.redisperformance.lock.NftGeneratorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class LockWinnerSelectorStrategy implements WinnerSelector {
    private final NftGeneratorUtil nftGeneratorUtil;

    @Override
    public void winnerSelect(String userId, Long pickNumber) {
        RLock lock = nftGeneratorUtil.rLock();
        try {
            if(!lock.tryLock(1, 1, TimeUnit.SECONDS)) {
                log.info("여기까지인가보오.");
                throw new InterruptedException("Lock Exception");
            }

            defaultWinnerSelector(nftGeneratorUtil, userId, pickNumber);

        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }
}
