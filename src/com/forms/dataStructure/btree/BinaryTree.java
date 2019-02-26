package com.forms.dataStructure.btree;

/**
 * 二叉树接口
 * 根据不同的存储结构，可以有不同的实现类
 * @author v_dongzhao
 *
 */
public interface BinaryTree {
	
	public boolean isEmpty();
	
	public int size();
	
	public int getHight();
	
	public Node findKey(Object value);
	
	/**
	 * 递归前序遍历二叉树
	 */
	public void preOrderTraverse();
	
	public void inOrderTraverse();
	
	public void postOrderTraverse();
	
	public void postOrderTraverse(Node node);
	
	/**
	 * 非递归操作前序遍历二叉树（借助栈）
	 */
	public void preOrderByStack();
	
	public void inOrderByStack();
	
	public void postOrderByStack();
	
	/**
	 * 按层次遍历二叉树(借助队列)
	 */
	public void levelOrderByQueue();
}
