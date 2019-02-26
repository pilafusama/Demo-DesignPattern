package com.forms.bridge;

public interface Brand {
	void work();
}

class Lenovo implements Brand {

	@Override
	public void work() {
		System.out.println("Lenovo work");
	}
	
}

class Dell implements Brand {

	@Override
	public void work() {
		System.out.println("Dell work");
	}
	
}