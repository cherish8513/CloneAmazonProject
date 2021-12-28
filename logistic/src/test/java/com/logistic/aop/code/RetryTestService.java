package com.logistic.aop.code;

import com.logistic.aop.annotation.Retry;

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
