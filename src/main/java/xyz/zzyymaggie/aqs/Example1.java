package xyz.zzyymaggie.aqs;

import java.util.concurrent.locks.LockSupport;

/**
 * 采用park实现线程同步代码演示
 * @link https://www.bilibili.com/video/BV1d4411m7Pa
 */
public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("-------------m1----------------");
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                System.out.println("----------------t1---------------");
                LockSupport.park();
                System.out.println("---------------t2-----------------");
            }
        };
        t1.start();

        Thread.sleep(5000);
        System.out.println("--------------------m2--------------");
        LockSupport.unpark(t1);
    }
}
