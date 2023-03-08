package org.example.thread;

public class Demo  {

    public static class MyThread extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程A");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
     //   myThread.getThreadGroup()
        System.out.println("主线程已结束");
    }

    public void f(){

    }


    public static class MyThreadC extends Thread{

        @Override
        public void run(){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程A");
        }
    }


}
