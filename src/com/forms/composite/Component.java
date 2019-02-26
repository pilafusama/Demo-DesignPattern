package com.forms.composite;

/**
 * 组合模式，特别适用于对树形结构的对象集进行处理
 * @author v_dongzhao
 *
 */

//抽象组件：定义了叶子和容器构建的共同点
public interface Component {
	void operation();
}

//叶子组件：无子节点
interface Leaf extends Component {
	
}

//容器组件：有容器特征，可以包含子节点
interface Composite extends Component {
	void add(Component component);
	void remove(Component component);
	Component getChild(int index);
}

