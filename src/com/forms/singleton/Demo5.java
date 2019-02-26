package com.forms.singleton;

/**
 * 内部类
 * @author v_dongzhao
 *
 */
public class Demo5 {
	private Demo5() {
	}
	
	// 类加载时不会立刻加载静态内部类，调用getInstance方法时才会加载
	// 在内部类进行初始化时，别的线程是无法进入的。
	private static class SingletonInstance {
		private static final Demo5 d = new Demo5();
	}
	
	//避免了线程不安全，延迟加载，效率高，推荐使用
	public static Demo5 getInstance() {
		return SingletonInstance.d;
	}
}
