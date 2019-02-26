package com.forms.observer;

import java.util.ArrayList;
import java.util.List;

public class WeiboTest {
	public static void main(String[] args) {
		VSubject v = new VSubject();
		List<FObserver> fans = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			FObserver fan = new FObserver("name" + (i + 1));
			fans.add(fan);
			v.addFans(fan);
		}
		
		v.publish("我发微博了");
		
		for (int i = 0; i < 3; i++) {
			v.removeFans(fans.get(i));
		}
		
		v.publish("我又发微博了");
	}
}
