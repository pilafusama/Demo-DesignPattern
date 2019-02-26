package com.forms.factory.factoryMethod;

/**
 * 工厂方法模式，符何开闭原则，不用修改原来代码即可扩展
 * 弊端：增加类的个数
 * 根据设计模式理论，工厂方法模式占优，但实际项目开发中简单工厂模式更多
 * @author v_dongzhao
 *
 */
public class Client {
	public static void main(String[] args) {
		Car c1 = new AudiFactory().createCar();
		Car c2 = new BenzFactory().createCar();
		c1.run();
		c2.run();
	}
}
