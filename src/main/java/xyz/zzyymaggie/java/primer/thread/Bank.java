/*
 * @(#)Bank.java
 */
package xyz.zzyymaggie.java.primer.thread;

public class Bank{
    private int account = 100;

    public int getAccount() {
        return account;
    }

    /**
     * synchronized method
     * 
     * @param money
     */
    public synchronized void save(int money) {
        account += money;
    }

    /**
     * synchronized block
     * 
     * @param money
     */
    public void save1(int money) {
        synchronized (this) {
            account += money;
        }
    }
    /**
     * common method
     * 
     * @param money
     */
    public void save2(int money) {
        account += money;
    }
}
