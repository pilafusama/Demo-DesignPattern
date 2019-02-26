package com.forms.builder;

public class MyAirShipDirector implements AirShipDirector{

	// 需要什么类，就加入该类属性，以及一个含该类的构造器
	private AirShipBuilder builder;
	
	public MyAirShipDirector(AirShipBuilder builder) {
		this.builder = builder;
	}
	
	@Override
	public AirShip directAirShip() {
		Part1 part1 = builder.buildPart1();
		Part2 part2 = builder.buildPart2();
		Part3 part3 = builder.buildPart3();
		
		AirShip ship = new AirShip();
		ship.setPart1(part1);
		ship.setPart2(part2);
		ship.setPart3(part3);
		
		return ship;
	}

}
