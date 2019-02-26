package com.forms.observer;

public interface Subject {
	public void addFans(Observer fan);
	public void removeFans(Observer fan);
	public void notifyFans(String msg);
}
