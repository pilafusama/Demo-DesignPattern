package com.forms.singleton;

/**
 * 懒汉式
 * @author v_dongzhao
 *
 */
public class Demo2 {
	private static Demo2 d;
	private Demo2() {
	}
	// 延迟加载，但是需要同步，效率低
	public static synchronized Demo2 getInstance() {
		if (d == null) {
			d = new Demo2();
		}
		return d;
	}
}
