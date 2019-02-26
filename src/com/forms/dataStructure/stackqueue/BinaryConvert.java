package com.forms.dataStructure.stackqueue;

import java.util.Deque;
import java.util.LinkedList;

public class BinaryConvert {
	public static void main(String[] args) {
		System.out.println(convert(100));
	}
	
	public static String convert(int a) {
		Deque<Integer> stack = new LinkedList<Integer>();
		while (a>0) {
			stack.push(a%2);
			a = a/2;
		}
		StringBuilder sb = new StringBuilder(8);
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
}
