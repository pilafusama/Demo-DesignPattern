package com.forms.Thread;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 高并发容器
 *
 */
public class ConcurrentMap {
	public static void main(String[] args) {
		Map<String, String> map = new Hashtable<String, String>();
//		Map<String, String> map = new ConcurrentHashMap<String, String>();
//		Map<String, String> map = new ConcurrentSkipListMap<String, String>();
		final Random random = new Random();
		Thread[] array = new Thread[100];
		final CountDownLatch countDownLatch = new CountDownLatch(array.length);
		long begin = System.currentTimeMillis();
		
		for (int i = 0; i < array.length; i++) {
			array[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10000; j++) {
						map.put("key"+random.nextInt(100000), "value"+random.nextInt(100000));
					}
					countDownLatch.countDown();
				}
			});
		}
		for (Thread t : array) {
			t.start();
		}
		Executors.newFixedThreadPool(5);
		int i = Integer.MAX_VALUE;
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("耗时"+(end-begin)+"毫秒");
	}
}
