package org.ack.common.tree;

import java.util.List;

public class Tree extends Node {
	private int size;

	public Tree(Object value) {
		this.value = value;
		this.parent = null;
	}
	
	public void sort(List<Node> list){
		Object pValue = value;
		
		int i = 0;
		int size = list.size();
		while(size > 0){
			if(i == size - 1){
				break;
			}
			Node node = list.get(i);
			Node p = node.getParent();
			if(pValue.equals(p.getValue())){
				i++;
				Node tmp = node;
				list.remove(i);
				list.add(0, tmp);
			}
		}
	}
	
	public void add(List<Node> list) {
		if(null != list){
			for(Node n : list){
				add(n);
			}
			System.out.println("---------add complete---------");
		}
	}

	public void add(Node node) {
		if (null == node) {
			return;
		}
		// 查找父节点
		Node parent = findParent(node);
		parent.getChildren().add(node);

	}

	/**
	 * 查询当前node的父节点
	 * 
	 * @param node
	 * @return
	 */
	public Node findParent(Node node) {
		Node p = findParent(node, value);
		return p;
	}

	public Node findParent(Node node, Object tmpValue) {

		System.out.println("当前 : " + node + ", 查询node : " + tmpValue);

		if (null == tmpValue) {
			tmpValue = value;
		}
		if (node.getParent().getValue().equals(tmpValue)) {
			return this;
		}
		List<Node> list = this.getChildren();
		Node n = find(node, list);
		return n;
	}

	/**
	 * 递归转循环
	 * 
	 * */
	private Node find(Node node, List<Node> list) {
		int size = list.size();
		int i = 0;
		while (size > 0) {
			Node n = list.get(i);
			i++;
			if (n.getValue().equals(node.getParent().getValue())) {
				return n;
			} else {
				if (i == size) {
					list = n.getChildren();
					size = list.size();
					i = 0;
				}
			}

		}
		return null;
	}

	public List<Node> findChildren(Node node) {
		List<Node> children = node.getChildren();
		if (null == children || children.size() == 0) {
			return null;
		}
		return node.getChildren();
	}

	public int size() {
		return size;
	}

	public void show() {
		List<Node> list = this.getChildren();
		for (Node n : list) {
			System.out.println(n.getValue());
		}
	}

	
}
