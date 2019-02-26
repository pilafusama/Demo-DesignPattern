package com.forms.builder;

public class Part3 {
	private String name;

	public Part3(String name) {
		super();
		this.name = name;
	}
	
	public void work() {
		System.out.println("part3 work");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
