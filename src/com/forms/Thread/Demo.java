package com.forms.Thread;

/**
 * 《java并发编程实践》3.1.1 过期数据
 * @author v_dongzhao
 *
 */
public class Demo {
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {
		public void run() {
			
			// 这条线程读到的数据有可能过期（不是最新的数据）
			while (!ready) {
				Thread.yield();
				System.out.println(number);
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		new ReaderThread().start();
		System.out.println(Thread.currentThread().getName());
		number = 42;
		ready = true;
	}
}


