package com.example.awssdk.cloudWatch;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@SpringBootTest
@ActiveProfiles("prod")
public class HelloCloudWatch {

    @Test
    public void tooManyError() {
        for (int i = 0; i < 20; i++) {
            log.error("에러 로그 발생" + i);
        }
    }

    @Test
    public void throw404Error() {
        try {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
