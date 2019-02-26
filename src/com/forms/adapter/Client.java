package com.forms.adapter;

/**
 * 适配器模式：通过适配器将已有的A类转换成符何要求的B类。
 * @author v_dongzhao
 *
 */
public class Client {
	public static void main(String[] args) {
		Client client = new Client();
		Target target = new Adapter(new Adaptee());
		client.work(target);
	}
	
	public void work(Target target) {
		target.run();
	}
}
