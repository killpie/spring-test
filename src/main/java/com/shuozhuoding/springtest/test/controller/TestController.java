package com.shuozhuoding.springtest.test.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncManager;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("test")
public class TestController {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
  private static final Map<String, DeferredResult<String>> taskMap = new ConcurrentHashMap<>();

  @RequestMapping("/async-deferredresult")
  public DeferredResult<String> test() {
    LOGGER.info("request task start");
    WebAsyncManager d;
    DeferredResult<String> deferredResult = new DeferredResult<>();

    ForkJoinPool.commonPool()
        .execute(
            () -> {
              LOGGER.info("work task start");
              try {
                Thread.sleep(3000);
                deferredResult.setResult("OK");
              } catch (InterruptedException e) {
                LOGGER.error("", e);
                deferredResult.setErrorResult(e);
              } finally {
                LOGGER.info("work task end");
              }
            });
    deferredResult.onCompletion(
        () -> {
          LOGGER.info("onCompletion");
        });
    LOGGER.info("request task end");
    return deferredResult;
  }

  @RequestMapping("/test1/test")
  public Callable<String> get1() {
    Callable<String> callable =
        () -> {
          System.out.println(Thread.currentThread().getName() + " 子子子线程start");
          TimeUnit.SECONDS.sleep(5); // 模拟处理业务逻辑，话费了5秒钟
          System.out.println(Thread.currentThread().getName() + " 子子子线程end");

          // 这里稍微小细节一下：最终返回的不是Callable对象，而是它里面的内容
          return "";
        };
    return callable;
  }

  @RequestMapping("/createTask")
  public String createTask() {
    String uuid = UUID.randomUUID().toString();
    DeferredResult<String> deferredResult = new DeferredResult<>(1000000L);
    taskMap.put(uuid, deferredResult);
    return uuid;
  }


  @RequestMapping("/queryTaskState")
  public String queryTaskState(String uuid) {
    DeferredResult<String> deferredResult = taskMap.get(uuid);
    if (deferredResult == null) {
      return "未查询到任务,uid:" + uuid;
    }
    if (deferredResult.hasResult()) {
      return deferredResult.getResult().toString();
    } else {
      return "进行中";
    }
  }

  @RequestMapping("/changeTaskState")
  public String changeTaskState(String uuid) {
    DeferredResult<String> deferredResult = taskMap.remove(uuid);
    if (deferredResult == null) {
      return "未查到到任务";
    }
    if (deferredResult.hasResult()) {
      return "已完成，无需再次设置";
    } else {
      deferredResult.setResult("已完成");
      return "已完成";
    }
  }
}
