package com.forms.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 测试是否为单例
 * @author v_dongzhao
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		Demo d1 = Demo.getInstance();
		Demo d2 = Demo.getInstance();
		System.out.println(d1);
		System.out.println(d2);
		
		//反射方式破坏单例
		Class<Demo> clazz = (Class<Demo>) Class.forName("com.forms.singleton.Demo");
		Constructor<Demo> constructor = clazz.getDeclaredConstructor(null);
		constructor.setAccessible(true); // 构造方法被私有化，不设置会报错
		Demo d3 = constructor.newInstance();
		Demo d4 = constructor.newInstance();
		System.out.println(d3);
		System.out.println(d4);
		
		//反序列化方式破坏单例
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/a.txt"));
		oos.writeObject(d1);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/a.txt"));
		Demo d5 = (Demo) ois.readObject();
		ois.close();
		System.out.println(d5);
		
		
	}
}
