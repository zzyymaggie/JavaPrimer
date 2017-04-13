/*
 * @(#)Bank2.java
 **/
package xyz.zzyymaggie.java.primer.thread;

public class Bank2 {
    private static ThreadLocal<Integer> account = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 100;
        }
    };
    public void save(int money){
        account.set(account.get()+money);
    }
    public int getAccount(){
        return account.get();
    }
}

