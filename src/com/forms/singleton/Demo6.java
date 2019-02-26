package com.forms.singleton;

/**
 * 枚举，天然单例，还能防止通过反射和反序列化来创建多个对象
 * @author v_dongzhao
 *
 */
public enum Demo6 {
	SINGLETON;
	
	public void whateverMethod() {
		
	}
}
