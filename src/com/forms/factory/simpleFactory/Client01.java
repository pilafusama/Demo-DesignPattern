package com.forms.factory.simpleFactory;

/**
 * 没有工厂模式时创造对象
 * 既要知道接口也要知道实现类，同时与两者耦合
 * 实现类越来越多的时候，就会很复杂
 * @author v_dongzhao
 *
 */
public class Client01 {
	public static void main(String[] args) {
		Car c1 = new Audi();
		Car c2 = new Benz();
		c1.run();
		c2.run();
	}
}
