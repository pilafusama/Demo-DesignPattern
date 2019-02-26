package com.forms.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理，关键是handler
 * @author v_dongzhao
 *
 */
public class Client {
	public static void main(String[] args) {
		Star star = new RealStar();
		StarHandler handler = new StarHandler(star);
		
		Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] {Star.class}, handler);
		proxy.confer();
		proxy.signContract();
		proxy.sing();
		proxy.collectMoney();
	}
}
