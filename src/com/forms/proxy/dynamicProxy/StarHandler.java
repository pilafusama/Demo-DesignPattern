package com.forms.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler实现类是动态代理的关键
 * 持有被代理对象
 * @author v_dongzhao
 *
 */
public class StarHandler implements InvocationHandler{
	private Object obj;
	
	public StarHandler(Object obj) {
		super();
		this.obj = obj;
	}

	// proxy是代理类对象可以使用反射获取代理对象的信息（也就是proxy.getClass().getName()）
	//可以将代理对象返回以进行连续调用，这就是proxy存在的目的，因为this并不是代理对象
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object res = null;
		if ("sing".equals(method.getName())) {
			System.out.println("前处理。。。");
			res = method.invoke(obj, args);
			System.out.println("后处理。。。");
		} else {
			res = method.invoke(obj, args);
		}
		
		return res;
	}

}
