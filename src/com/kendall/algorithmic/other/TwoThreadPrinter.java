package com.kendall.algorithmic.other;

/**
 * @description:两个线程顺序打印1-100的数字，要求一个现场打印奇数一个打印偶数
 * @author: kendall
 * @since: 2019/3/12
 */
public class TwoThreadPrinter {
    private static Object lock = new Object();
    private static int count = 1;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadPrinter("奇数"));
        Thread thread1 = new Thread(new ThreadPrinter("偶数"));
        thread.start();
        Thread.sleep(10);
        thread1.start();

    }

    private static class ThreadPrinter implements Runnable {
        String name;

        public ThreadPrinter(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ": " + name + ": " + count++);
                    lock.notify();
                    try {
                        if (count <= 100) {
                            lock.wait();
                        }
//                    Thread.currentThread().wait();是不行的
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
