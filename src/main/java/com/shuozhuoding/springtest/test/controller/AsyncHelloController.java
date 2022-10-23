package com.shuozhuoding.springtest.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/async/controller")
public class AsyncHelloController {

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
}
