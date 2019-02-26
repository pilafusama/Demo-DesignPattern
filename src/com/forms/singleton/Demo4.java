package com.forms.singleton;

/**
 * 懒汉式加强版
 * @author v_dongzhao
 *
 */
public class Demo4 {
	private static volatile Demo4 d;
	private Demo4() {
	}
	
	//线程安全；延迟加载；效率较高。推荐使用
	public static Demo4 getInstance() {
		if (d == null) {
			synchronized (Demo4.class) {
				if (d == null) {
					d = new Demo4();
				}
			}
		}
		return d;
	}
}
