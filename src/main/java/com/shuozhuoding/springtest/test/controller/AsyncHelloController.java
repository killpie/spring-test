package com.shuozhuoding.springtest.test.controller;

import com.shuozhuoding.springtest.test.service.TestService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/async/controller")
public class AsyncHelloController {

    @Autowired
    private TestService testService;
    private final Logger logger = LoggerFactory.getLogger(AsyncHelloController.class);
    @ResponseBody
    @GetMapping("/hello")
    public Callable<String> helloGet() throws Exception {
        System.out.println(Thread.currentThread().getName() + " 主线程start");

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " 子子子线程start");
            TimeUnit.SECONDS.sleep(5); //模拟处理业务逻辑，话费了5秒钟
            System.out.println(Thread.currentThread().getName() + " 子子子线程end");

            // 这里稍微小细节一下：最终返回的不是Callable对象，而是它里面的内容
            return "hello world";
        };

        System.out.println(Thread.currentThread().getName() + " 主线程end");
        return callable;
    }

    @GetMapping("testAsync")
    public void testAsync(){
        logger.info("异步请求开始");
        new Thread(this::asyncExecute1).start();
        logger.info("异步请求结束");
    }

    @SneakyThrows
    public void asyncExecute1(){
        logger.info("业务处理开始");
        TimeUnit.SECONDS.sleep(10);
        logger.info("业务处理结束");
    }

    @GetMapping("testAsync1")
    public void testAsync1(){
        logger.info("异步请求开始");
        testService.asyncExecute2();
        logger.info("异步请求结束");
    }
}
