package com.forms.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 饿汉式
 * @author v_dongzhao
 *
 */
public class Demo implements Serializable {
	private static Demo d = new Demo(); //类初始化时就加载对象，加载类时天然线程安全
	private Demo() {
		//防止反射构造对象
		if (d != null) {
			throw new RuntimeException("对象已存在");
		}
	}
	// 方法无需同步，效率高，但是不能实现延迟加载，容易资源浪费
	public static Demo getInstance() {
		return d;
	}
	
	// 防止通过反序列化方式创建对象
	private Object readResolve() throws ObjectStreamException {
		return d;
	}
}
