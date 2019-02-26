package com.forms.builder;

public class Part1 {
	private String name;

	public Part1(String name) {
		super();
		this.name = name;
	}
	
	public void work() {
		System.out.println("part1 work");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
