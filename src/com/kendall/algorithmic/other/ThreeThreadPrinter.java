package com.kendall.algorithmic.other;

/**
 * @description:三个线程ABC，交替打印ABC
 * @author: kendall
 * @since: 2019/3/12
 */
public class ThreeThreadPrinter {
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Thread thread = new Thread(new ThreadPrinter("a", c, a));
        Thread thread1 = new Thread(new ThreadPrinter("b", a, b));
        Thread thread2 = new Thread(new ThreadPrinter("c", b, c));
        thread.start();
        Thread.sleep(10);
        thread1.start();
        Thread.sleep(10);
        thread2.start();

    }

    private static class ThreadPrinter implements Runnable {
        String name;
        Object prevLock;
        Object curLock;

        public ThreadPrinter(String name, Object prevLock, Object curLock) {
            this.name = name;
            this.prevLock = prevLock;
            this.curLock = curLock;
        }

        @Override
        public void run() {
            int count = 1;
            while (count <= 10) {
                synchronized (prevLock) {
                    synchronized (curLock) {
                        System.out.println(Thread.currentThread().getName() + ": " + name+":" +count);
                        count++;
                        curLock.notifyAll();
                    }
                    try {
                        if (count > 10) {
                            prevLock.notifyAll();
                        } else {
                            prevLock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
