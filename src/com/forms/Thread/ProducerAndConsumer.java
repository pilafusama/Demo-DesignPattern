package com.forms.Thread;

public class ProducerAndConsumer {
	private int[] array = new int[10];
	private int cnt = 0;
	
	public synchronized void add(int a) {
		while (cnt == 10) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		array[cnt] = a;
		System.out.println("添加了数据后，数组长度"+(cnt+1));
		cnt++;
		this.notifyAll();
	}
	
	public synchronized int get() {
		while (cnt == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		int a = array[cnt-1];
		System.out.println("取出数据的位置"+(cnt-1));
		cnt--;
		this.notifyAll();
		return a;
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
