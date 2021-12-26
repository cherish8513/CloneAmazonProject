package com.amazonClone.logisticSystem.aop.code;

import com.amazonClone.logisticSystem.aop.annotation.Retry;

public class RetryTestService {

    int seq = 0;

    @Retry
    public String unstableMethod(){
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("오류 발생");
        }
        return "ok";
    }
}
