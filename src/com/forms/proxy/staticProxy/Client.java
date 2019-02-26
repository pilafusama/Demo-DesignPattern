package com.forms.proxy.staticProxy;

/**
 * 代理模式实现控制对对象的访问
 * 访问A对象，来达到访问B对象的目的。A即B的代理，
 * 但是A中的方法与B不同，比如A中某个方法主体与B相同，但是调用这个方法时有前置处理，调用完成后有后置处理，即AOP。
 * 一般代理角色和真实角色（即被代理角色）都会实现一个抽象角色（定义公共对外方法），
 * 静态代理
 * @author v_dongzhao
 *
 */
public class Client {
	public static void main(String[] args) {
		Star real = new RealStar();
		Star proxy = new ProxyStar(real);
		
		proxy.confer();
		proxy.signContract();
		proxy.sing();
		proxy.collectMoney();
	}
	
}
