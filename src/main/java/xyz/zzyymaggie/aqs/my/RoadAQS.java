package xyz.zzyymaggie.aqs.my;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 自己动手实现一个同步器 在这个基础上，再看AQS的实现会更清晰，理解它为了高并发做的细节优化
 * 参考地址：https://www.cnblogs.com/maratong/p/12369907.html
 */
public class RoadAQS {
    //当前锁的状态，1表示加锁，0表示未加锁
    private volatile int state = 0;
    private final static Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    //state在内存中的偏移量
    private final static long stateOffset;
    //当前持有锁的线程
    private Thread lockHolder;
    //是一个线程安全的队列，记录等待获取锁的线程
    private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(RoadAQS.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }

    /**
     * peek- 查询 - 只检索，不删除Queue的头部元素
     * poll- 出队 - 检索并删除Queue的头部元素
     * add - 入队
     */
    public void lock() {
        if(acquire()){
            return;
        }
        //将当前线程加入到等待队列
        Thread current = Thread.currentThread();
        waiters.add(current);
        for(;;) {
            //再尝试一次获取锁
            if(current == waiters.peek() && acquire()) {
                waiters.poll();
                return;
            }
            //LockSupport.park()将该线程进行阻塞，释放cpu
            LockSupport.park();
        }
    }

    /**
     * 当等待队列为空或者当前线程等于等待队列的第一个线程，然后CAS更新状态为1成功，说明获得锁成功
     * 当等待队列为空 对应 首次进入场景
     * 当前线程等于等待队列的第一个线程 对应什么场景呢？unpark唤醒的线程
     */
    public boolean acquire() {
        Thread t = Thread.currentThread();
        if ((waiters.size() == 0 || t == waiters.peek()) && compareAndSwapInt(0, 1)) {
            setLockHolder(t);
            return true;
        }
        return false;
    }

    public void unlock() {
        if (Thread.currentThread() != getLockHolder()) {
            throw new RuntimeException("lockHolder is not current Thread");
        }
        int state = getState();
        if (compareAndSwapInt(state, 0)) {
            setLockHolder(null);
            //得到要唤醒的线程头部线程
            Thread t = waiters.peek();
            if (t != null) {
                //唤醒等待线程
                LockSupport.unpark(t);
            }
        }
    }

    protected final boolean compareAndSwapInt(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }
}
