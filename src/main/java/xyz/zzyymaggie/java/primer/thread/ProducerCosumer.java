package xyz.zzyymaggie.java.primer.thread;
/**
 * 马士兵_JavaSE基础教程第09章 线程 - 线程同步 - 生产者消费者问题
 * @author zzymaggie
 *
 */
public class ProducerCosumer {
	public static void main(String[] args) {
		SyncStack ss = new SyncStack();
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		new Thread(p).start();
		new Thread(c).start();
	}
}

class SyncStack {
	Wotou[] wotouArray = new Wotou[6];
	int index = 0;
	public synchronized void push(Wotou wotou) {
		while(index == wotouArray.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		wotouArray[index] = wotou;
		index++;
	}
	
	public synchronized Wotou pop() {
		while(index == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		index--;
		return wotouArray[index];
	}
}

class Wotou{
	private int id;
	
	public Wotou(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "wotou " + id;
	}
}

class Producer implements Runnable{
	SyncStack ss = null;
	public Producer(SyncStack ss) {
		this.ss = ss;
	}
	
	public void run() {
		for(int i=0;i<20;i++) {
			Wotou wotou = new Wotou(i);
			ss.push(wotou);
			System.out.println("produce " + wotou);
			try {
				Thread.sleep((int)(Math.random() * 200));
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable{
	SyncStack ss = null;
	public Consumer(SyncStack ss) {
		this.ss = ss;
	}
	
	public void run() {
		for(int i=0;i<20;i++) {
			Wotou wotou = ss.pop();
			System.out.println("consum " + wotou);
			try {
				Thread.sleep((int)(Math.random() * 1000));
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}