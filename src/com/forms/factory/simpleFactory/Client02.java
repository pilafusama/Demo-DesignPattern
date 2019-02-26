package com.forms.factory.simpleFactory;

/**
 * 简单工厂,也叫静态工厂模式，通过接收不同参数返回不同对象
 * 但是简单工厂对于新增产品无能为力，不修改代码无法扩展
 * @author v_dongzhao
 *
 */
public class Client02 {
	public static void main(String[] args) {
		Car c1 = CarFactory.createCar("benz");
		Car c2 = CarFactory.createCar("audi");
		c1.run();
		c2.run();
	}
}
