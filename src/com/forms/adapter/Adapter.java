package com.forms.adapter;

/**
 * 适配器
 * @author v_dongzhao
 *
 */
public class Adapter implements Target{
	private Adaptee adaptee;
	
	public Adapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void run() {
		adaptee.run();
	}
	
	
}
