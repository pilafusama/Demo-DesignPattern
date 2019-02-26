package com.forms.proxy.staticProxy;

/**
 * 静态代理类，与被代理类实现共同的接口
 * @author v_dongzhao
 *
 */
public class ProxyStar implements Star{
	private Star star;
	
	public ProxyStar(Star star) {
		this.star = star;
	}

	@Override
	public void confer() {
		System.out.println("ProxyStar.confer()");
	}

	@Override
	public void signContract() {
		System.out.println("ProxyStar.signContract()");
	}

	@Override
	public void sing() {
		star.sing();
	}

	@Override
	public void collectMoney() {
		System.out.println("ProxyStar.collectMoney()");
	}
	
}
