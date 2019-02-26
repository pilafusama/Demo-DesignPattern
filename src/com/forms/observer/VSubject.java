package com.forms.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * 被观察者(Subject)维护一个观察者(Observer)List
 * 维护意味着Subject有增删List中成员的方法
 * 当Subject做一件事后，每个Observer都能给出反应
 * @author v_dongzhao
 *
 */
public class VSubject implements Subject {
	List<Observer> fansList = new ArrayList<>();

	@Override
	public void addFans(Observer fan) {
		fansList.add(fan);
	}

	@Override
	public void removeFans(Observer fan) {
		fansList.remove(fan);
	}

	@Override
	public void notifyFans(String msg) {
		for (Observer fan : fansList) {
			fan.update(msg); //3，每个Observer给出反应
		}
	}
	
	public void publish(String msg) {
		System.out.println("大V发布了微博"); //1，Subject做一件事
		this.notifyFans(msg); //2，通知Observer
	}

}
