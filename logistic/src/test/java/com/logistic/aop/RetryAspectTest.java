package com.logistic.aop;

import com.logistic.aop.code.RetryTestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(RetryTestService.class)
public class RetryAspectTest {

    @Autowired
    RetryTestService retryTestService;

    @Test
    public void retryTest() throws Exception{
        for (int i = 0; i < 5; i++) {
            log.info("client request i = {}", i);
            retryTestService.unstableMethod();
        }
    }
}
