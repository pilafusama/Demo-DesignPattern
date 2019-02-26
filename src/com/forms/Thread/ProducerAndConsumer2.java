package com.forms.Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 相较于synchronized中的this.notifyAll,ReentrantLock条件的signalAll()更精准。
 * notifyAll会唤醒所有线程池中的线程去共同竞争，而signalAll只会唤醒部分，如本例中的消费者或者生产者
 * @author v_dongzhao
 *
 */
public class ProducerAndConsumer2 {
	private int[] array = new int[10];
	private int cnt = 0;
	
	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();
	
	public void add(int a) {
		lock.lock();
		try {
			while (cnt == 10) {
				producer.await();
			}
			array[cnt] = a;
			System.out.println("添加了数据后，数组长度"+(cnt+1));
			cnt++;
			consumer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void get() {
		lock.lock();
		try {
			while (cnt == 0) {
				consumer.await();
			}
			int a = array[cnt-1];
			System.out.println("取出数据的位置"+(cnt-1)+",值是"+a);
			cnt--;
			producer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
	
	public static void main(String[] args) {
		ProducerAndConsumer p = new ProducerAndConsumer();
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10; j++) {
						p.get();
					}
				}
			}).start();
		}
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10; j++) {
						p.add(j);
					}
				}
			}).start();
		}
		
	}
}
