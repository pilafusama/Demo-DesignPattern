package com.forms.decorator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 动态的为对象新增功能，取代继承，更加灵活，避免类型体系快速膨胀
 * XXXXWrapper类
 * 缺点是会产生很多小对象占内存，出错时调试麻烦
 * @author v_dongzhao
 *
 */
public class Client {
	public static void main(String[] args) {
		Car car = new MyCar();
		car.move();
		
		// 陆空
		Car car2 = new FlyCar(car);
		car2.move();
		
		// 水陆
		Car car3 = new WaterCar(car);
		car3.move();
		
		// 水陆空
		Car car4 = new WaterCar(new FlyCar(car));
		car4.move();
		
		try {
			Reader r = new BufferedReader(new InputStreamReader(new FileInputStream("D:/a.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
