package com.example.redisperformance.web;

import com.example.redisperformance.nftService.NftGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("nft")
@RequiredArgsConstructor
public class NftGenerateController {
    private final NftGenerator nftGenerator;

    @RequestMapping("mint")
    public String publish() throws InterruptedException {
        String userId = RandomStringUtils.randomAlphabetic(7);
        log.info(userId);
        nftGenerator.generate(userId);
        return "minted";
    }
}
