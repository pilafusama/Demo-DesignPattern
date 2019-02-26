package com.forms.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * 多线程模式下测试单例效率
 * @author v_dongzhao
 *
 */
public class Client2 {
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		int threadCount = 10;
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 100000; i++) {
						Object o = Demo2.getInstance();
					}
					
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await(); //countDownLatch计数减为0时才执行主线程
		
		long end = System.currentTimeMillis();
		System.out.println("耗时： " + (end - start));
	}
}
