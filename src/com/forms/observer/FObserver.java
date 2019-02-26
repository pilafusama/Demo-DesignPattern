package com.forms.observer;

public class FObserver implements Observer {

	private String name;
	
	public FObserver() {
		super();
	}
	
	public FObserver(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(String msg) {
		System.out.println(name + " 收到大V的微博发文： " + msg);
	}

}
