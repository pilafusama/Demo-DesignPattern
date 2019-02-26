package com.forms.builder;

public class MyAirShipBuilder implements AirShipBuilder{

	@Override
	public Part1 buildPart1() {
		System.out.println("build part1");
		return new Part1("mypart1"); //此处可用工厂模式
	}

	@Override
	public Part2 buildPart2() {
		System.out.println("build part2");
		return new Part2("mypart2");
	}

	@Override
	public Part3 buildPart3() {
		System.out.println("build part3");
		return new Part3("mypart3");
	}

}
