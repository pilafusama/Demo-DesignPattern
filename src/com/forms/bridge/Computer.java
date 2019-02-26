package com.forms.bridge;

public class Computer {
	protected Brand brand;

	public Computer(Brand brand) {
		super();
		this.brand = brand;
	}
	
	public void work() {
		brand.work();
	}
	
}

class DeskTop extends Computer {

	public DeskTop(Brand brand) {
		super(brand);
	}

	@Override
	public void work() {
		super.work();
		System.out.println("DeskTop work");
	}
	
}

class LapTop extends Computer {

	public LapTop(Brand brand) {
		super(brand);
	}

	@Override
	public void work() {
		super.work();
		System.out.println("LapTop work");
	}
	
}
