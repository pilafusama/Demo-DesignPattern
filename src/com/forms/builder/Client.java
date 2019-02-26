package com.forms.builder;

/**
 * 建造者模式：适用于多组件组装后共同工作
 * @author v_dongzhao
 *
 */
public class Client {
	public static void main(String[] args) {
		AirShipDirector director = new MyAirShipDirector(new MyAirShipBuilder());
		AirShip ship = director.directAirShip();
		ship.lunch();
		
	}
}
