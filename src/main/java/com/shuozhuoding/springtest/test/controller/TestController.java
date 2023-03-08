package com.shuozhuoding.springtest.test.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.config.Task;
import org.springframework.util.StopWatch;
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
  public DeferredResult<String> createTask(String uuid) {
    LOGGER.info("接口createTask，ID[{}]任务开始",uuid);
    StopWatch stopWatch = new StopWatch(" DeferredResult test 容器线程");
    stopWatch.start("容器线程");
    LOGGER.info("接口createTask，返回值DeferredResult 异步请求开始");
    //超时时间100s
    DeferredResult<String> deferredResult = new DeferredResult<>(100000L);
    StopWatch t = new StopWatch(" DeferredResult test 工作线程");
    t.start("工作线程");
    deferredResult.onCompletion(()->{
      LOGGER.info("接口createTask，返回值DeferredResult onCompletion 工作线程处理完毕");
      t.stop();
      LOGGER.info("接口createTask，{}秒",t.getTotalTimeSeconds());
    });
    taskMap.put(uuid, deferredResult);
    LOGGER.info("接口createTask，返回值DeferredResult 异步请求结束");
    stopWatch.stop();
    LOGGER.info("接口createTask，{}秒",stopWatch.getTotalTimeSeconds());
    return deferredResult;
  }
//887843e4-1034-4a1b-8a75-c89f23f51fb4

  @RequestMapping("/queryTaskState")
  public String queryTaskState(String uuid) {
    DeferredResult<String> deferredResult = taskMap.get(uuid);
    if (deferredResult == null) {
      return "未查询到任务,uid:" + uuid;
    }
    if (deferredResult.hasResult()) {
      return deferredResult.getResult().toString();
    } else {
      LOGGER.info("接口queryTaskState，ID[{}]任务进行中",uuid);
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
      //未完成设置为完成
      deferredResult.setResult("已完成");
      LOGGER.info("接口changeTaskState，将任务ID{},设置为处理完成",uuid);
      return "已完成";
    }
  }
}
