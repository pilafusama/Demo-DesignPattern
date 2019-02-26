package com.forms.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyContainer {
	volatile List list = new ArrayList();
	
	public void add(Object o) {
		list.add(o);
	}
	
	public int size() {
		return list.size();
	}
	
	public static void main(String[] args) {
		final MyContainer c = new MyContainer();
		
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		new Thread(() -> {
			System.out.println("t2启动");
			/*while(true) {
				if (c.size() ==5) {
					break;
				}
			}*/
			if (c.size() != 5) {
				try {
					countDownLatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("t2结束");
		},"t2").start();
		
		new Thread(() ->  {
			System.out.println("t1启动");
			for (int j = 0; j < 10; j++) {
				c.add(new Object());
				System.out.println("add "+j);
				if (c.size() == 5) {
					countDownLatch.countDown();
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1").start();
		

		
	}
}
