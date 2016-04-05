/*
 * @(#)Sync.java
 */
package xyz.zzyymaggie.java_primer.thread;

/**
 * Synchronized Thread Example
 * 
 */
public class SynchronizedThread {
    class NewThread implements Runnable {
        private Bank bank;
        private Bank2 bank2;

        public NewThread(Bank bank) {
            this.bank = bank;
        }
        
        public NewThread(Bank2 bank) {
            this.bank2 = bank;
        }

        public void run() {
            for (int i = 0; i < 100; i++) {
                if(bank != null) {
                    bank.save(10);
                    System.out.println(i + "账户余额为：" + bank.getAccount());
                }
                if(bank2 != null){
                    bank2.save(10);
                    System.out.println(i + "账户余额为：" + bank2.getAccount());
                }
            }
        }

    }

    /**
     * create Thread by call Bank
     */
    public void useThread() {
        Bank bank = new Bank();
        NewThread new_thread = new NewThread(bank);
        System.out.println("线程1");
        Thread thread1 = new Thread(new_thread);
        thread1.start();
        System.out.println("线程2");
        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }
    
    /**
     * create Thread by call Bank2
     */
    public void useThread2() {
        Bank2 bank = new Bank2();
        NewThread new_thread = new NewThread(bank);
        System.out.println("线程1");
        Thread thread1 = new Thread(new_thread);
        thread1.start();
        System.out.println("线程2");
        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }

    public static void main(String[] args) {
        SynchronizedThread st = new SynchronizedThread();
        st.useThread2();
    }
}

