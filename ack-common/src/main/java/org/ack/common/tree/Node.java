package org.ack.common.tree;

import java.util.LinkedList;
import java.util.List;

public class Node {
	
	private Object value; // 当前节点值
	private Node parent; // 父节点
	private List<Node> children = new LinkedList<Node>(); // 子节点
	

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isLeaf() {
		if (null == children || children.size() == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

}