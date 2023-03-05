package com.shuozhuoding.springtest.test.service;

import com.shuozhuoding.springtest.test.controller.AsyncHelloController;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Async
    @SneakyThrows
    public void asyncExecute2(){
        logger.info("业务处理开始");
        TimeUnit.SECONDS.sleep(10);
        logger.info("业务处理结束");
    }
}
