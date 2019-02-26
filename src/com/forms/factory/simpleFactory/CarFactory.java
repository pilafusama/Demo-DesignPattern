package com.forms.factory.simpleFactory;

/**
 * 简单工厂模式
 * 也叫静态工厂模式，通过接收不同参数返回不同对象
 * 但是简单工厂对于新增产品无能为力，不修改代码无法扩展，违反开闭原则，对扩展开发，对修改关闭
 * @author v_dongzhao
 *
 */
public class CarFactory {
	public static Car createCar(String type) {
		if ("audi".equals(type)) {
			return new Audi();
		} else if ("benz".equals(type)) {
			return new Benz();
		} else {
			return null;
		}
	}
	
/*	public static Car createAudi() {
		return new Audi();
	}
	
	public static Car createBenz() {
		return new Benz();
	}*/
}
