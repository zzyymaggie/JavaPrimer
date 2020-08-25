package xyz.zzyymaggie.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock公平锁实现线程同步代码演示，
 * AQS 默认规则：第一个节点不排队，类比买火车票，第一个是正在买票的人，后面人才是排队的人
 * 队首线程：
 *  * 持有锁的线程
 *  * 虚拟出来的一个节点
 * @see https://www.bilibili.com/video/BV1d4411m7Pa
 */
public class Example2 {

    public static void main(String[] args) {
        //重入锁、排他锁
        final ReentrantLock lock = new ReentrantLock(true);

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                lock.lock();

                logic();

                lock.unlock();
            }
        };
        t1.start();
        //保证t1先执行
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                lock.lock();
                System.out.println("t2");
                lock.unlock();
            }
        };
        t2.start();
    }

    public static void logic() {
        System.out.println("111");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("222");
    }

}
