package com.forms.dataStructure.btree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedBinaryTree implements BinaryTree{
	private Node root;

	public LinkedBinaryTree() {
		super();
	}

	public LinkedBinaryTree(Node root) {
		super();
		this.root = root;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		System.out.println("二叉树的节点数是：");
		return size(root);
	}
	
	private int size(Node node) {
		if (node != null) {
			int i = size(node.leftChild);
			int j = size(node.rightChild);
			return i+j+1;
		} else {
			return 0;
		}
	}

	@Override
	public int getHight() {
		System.out.println("二叉树的高度是：");
		return getHight(root);
	}
	
	private int getHight(Node node) {
		if (node != null) {
			int i = getHight(node.leftChild);
			int j = getHight(node.rightChild);
			return i>=j ? i+1 : j+1;
		} else {
			return 0;
		}
	}

	@Override
	public Node findKey(Object value) {

		return findKey(value, root);
	}
	
	private Node findKey(Object value, Node node) {
		if (node == null) {
			return null;
		} else if (node.data == value) {
			return node;
		} else {
			Node node1 = findKey(value, node.leftChild);
			Node node2 = findKey(value, node.rightChild);
			if (node1 != null && node1.data == value) {
				return node1;
			} else if (node2 != null && node2.data == value) {
				return node2;
			} else {
				return null;
			}
		}
	}

	/**
	 * 这种实现方法没有中序好
	 */
	@Override
	public void preOrderTraverse() {
		if (root != null) { //此处root容易有歧义
			//输出根节点
			System.out.print(root.data + " ");
			//对左子树先序遍历
			BinaryTree tree1 = new LinkedBinaryTree(root.leftChild);
			tree1.preOrderTraverse();
			//对右子树先序遍历
			BinaryTree tree2 = new LinkedBinaryTree(root.rightChild);
			tree2.preOrderTraverse();
		}
	}

	@Override
	public void inOrderTraverse() {
		System.out.println("中序遍历：");
		inOrderTraverse(root);
		System.out.println();
	}
	
	private void inOrderTraverse(Node node) {
		if (node != null) {
			inOrderTraverse(node.leftChild);
			System.out.print(node.data + " ");
			inOrderTraverse(node.rightChild);
		}
	}

	@Override
	public void postOrderTraverse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postOrderTraverse(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preOrderByStack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inOrderByStack() {
		System.out.println("中序非递归遍历");
		Deque<Node> stack = new LinkedList<Node>();
		Node current = root;
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				stack.push(current);
				current = current.leftChild;
			}
			
			if (!stack.isEmpty()) {
				current = stack.pop();
				System.out.print(current.data + " ");
				current = current.rightChild;
			}
		}
		System.out.println();
	}

	@Override
	public void postOrderByStack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levelOrderByQueue() {
		System.out.println("层次遍历");
		if (root == null)
			return;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (queue.size() != 0) {
			int length = queue.size();
			for (int i = 0; i < length; i++) {
				Node node = queue.poll();
				System.out.print(node.data + " ");
				if (node.leftChild != null)
					queue.add(node.leftChild);
				if (node.rightChild != null)
					queue.add(node.rightChild);
			}
		}
		System.out.println();
	}

}
