package xyz.zzyymaggie.java.primer.thread;

/**
 * Synchronized keyword Example
 */
public class SyncronizedThread2 implements Runnable {  
    private static Object fSyncPOSObj = new Object();
    public void run() {  
         synchronized(fSyncPOSObj) {  
              for (int i = 0; i < 5; i++) {  
                   System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);  
              }  
         }  
    }  
    public static void main(String[] args) {  
         SyncronizedThread2 t1 = new SyncronizedThread2();  
         Thread ta = new Thread(t1, "A");  
         Thread tb = new Thread(t1, "B");  
         ta.start();  
         tb.start();  
    } 
}