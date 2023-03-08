import org.junit.jupiter.api.Test;

public class Test1 {
    @Test
    public void blockedTest() throws InterruptedException {

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();
        Thread.sleep(1000);

        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
        Thread.sleep(4000);
    }

    // 同步方法争夺锁
    private synchronized void testMethod() {
        try {
            System.out.println(String.format("线程：%s 运行，准备进入等待状态，让渡线程执行，状态：%s",Thread.currentThread().getName(),
                    Thread.currentThread().getState()));

            Thread.currentThread().join(2100);


            System.out.println(String.format("线程：%s 结束等待，准备进入就绪状态，状态：%s",Thread.currentThread().getName(),Thread.currentThread().getState()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
