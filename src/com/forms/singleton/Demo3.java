package com.forms.singleton;

public class Demo3 {
	private static Demo3 d;
	private Demo3() {
	}
	
	// 为了提高效率，使用更加细粒度的同步，但实际上不能保证单例
	public static Demo3 getInstance() {
		if (d == null) {
			synchronized (Demo3.class) {
				d = new Demo3();
			}
		}
		return d;
	}
}
