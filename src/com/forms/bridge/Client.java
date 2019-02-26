package com.forms.bridge;

/**
 * 桥接模式，可以取代多层继承结构，极大减少子类个数，提高扩展性
 * 需要新增电脑类型或者品牌时，在对应维度新增即可，而不用新增多个子类
 * 特别适用于，最小粒度的对象是按多个维度分类的
 * @author v_dongzhao
 *
 */
public class Client {
	public static void main(String[] args) {
		Computer computer = new LapTop(new Lenovo());
		computer.work();
		
		Computer computer2 = new DeskTop(new Dell());
		computer2.work();
	}
}
