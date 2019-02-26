package com.forms.dataStructure.btree;

public class Node {
	Object data;
	Node leftChild;
	Node rightChild;
	
	public Node(Object data) {
		super();
		this.data = data;
	}

	public Node(Object data, Node leftChild, Node rightChild) {
		super();
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
	}

}
