package xyz.zzyymaggie.pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * ObjectPool Tester.
 *
 * @author zhangyu
 * @version 1.0
 * @since <pre>09/23/2019</pre>
 */
public class ObjectPoolTest {
    private static ExecutorService batchExecutorService;
    private ObjectPool objPool = new ObjectPool();

    @Before
    public void before() {
        objPool.createPool();
        batchExecutorService = Executors.newFixedThreadPool(10);
    }

    @After
    public void after() {
        if (objPool != null) {
            objPool.closeObjectPool();
        }
    }

    /**
     * 对象归还后再获取元素
     */
    @Test
    public void testGetObject() throws Exception {
        Object obj1 = objPool.getObject();
        objPool.returnObject(obj1);
        Object obj2 = objPool.getObject();
        objPool.returnObject(obj2);
        assertTrue(obj1 == obj2);
    }

    /**
     * 对象不归还再次获取元素
     */
    @Test
    public void testGetObject2() throws Exception {
        Object obj1 = objPool.getObject();
        Object obj2 = objPool.getObject();
        objPool.returnObject(obj1);
        objPool.returnObject(obj2);
        assertFalse(obj1 == obj2);
    }

    @Test
    public void testGetObjectByMultiThreads() {
        for (int i = 0; i < 100; i++) {
            batchExecutorService.submit(() -> {
                Object obj1 = objPool.getObject();
                //模拟对象使用1ms
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //归还对象
                objPool.returnObject(obj1);
                System.out.println(obj1);
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
} 
