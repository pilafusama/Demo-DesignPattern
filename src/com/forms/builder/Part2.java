package com.forms.builder;

public class Part2 {
	private String name;

	public Part2(String name) {
		super();
		this.name = name;
	}
	
	public void work() {
		System.out.println("part2 work");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
