package com.forms.decorator;

public interface Car {
	void move();
}

// 被装饰对象
class MyCar implements Car{

	@Override
	public void move() {
		System.out.println("地上跑");
	}
}

// 装饰器
class SuperCar implements Car {
	// 必须持有被装饰对象
	protected Car car;

	public SuperCar(Car car) {
		this.car = car;
	}
	
	@Override
	public void move() {
		car.move();
	}
}

// 具体装饰角色
class FlyCar extends SuperCar {

	public FlyCar(Car car) {
		super(car);
	}
	
	public void fly() {
		System.out.println("天上飞");
	}

	@Override
	public void move() {
		super.move();
		fly();
	}
}

//具体装饰角色
class WaterCar extends SuperCar {

	public WaterCar(Car car) {
		super(car);
	}
	
	public void swim() {
		System.out.println("水里游");
	}

	@Override
	public void move() {
		super.move();
		swim();
	}
}
