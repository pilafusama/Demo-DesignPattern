package com.forms.dataStructure.btree;

public class Test {
	public static void main(String[] args) {
		// 创建树
		Node node5 = new Node(5, null, null);
		Node node4 = new Node(4, null, node5);
		Node node3 = new Node(3, null, null);
		Node node7 = new Node(7, null, null);
		Node node6 = new Node(6, null, node7);
		Node node2 = new Node(2, node3, node6);
		Node node1 = new Node(1, node4, node2);
		
		BinaryTree btree = new LinkedBinaryTree(node1);
		
		System.out.println("先序遍历：");
		btree.preOrderTraverse();
		System.out.println();
		
		btree.inOrderTraverse();
		
		btree.levelOrderByQueue();
		
		btree.inOrderByStack();
		
		System.out.println(btree.getHight());
		
		System.out.println(btree.size());
		
		System.out.println(btree.findKey(6));
	}
}
