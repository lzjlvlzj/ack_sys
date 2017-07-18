package org.ack.common.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	Node root = null; // 根节点
	Object value = null; // 根节点值

	public Tree(Node root, Object v) {
		this.root = root;
		this.value = v;
	}
	
	public Tree() {
	}

	public Tree(Object v) {
		this.root = new Node();
		this.value = v;
		this.root.setValue(v);
	}

	/**
	 * 添加
	 */
	public void add(Node node) {
		// 根据value获得父节点
		Node parent = find(node.getParent());
		parent.getChildren().add(node);
	}

	/**
	 * 查找
	 * */
	public Node find(Node node) {
		Node tmp = root;
		// 查找当前
		Object vl = tmp.getValue();
		Object nodeVal = node.getValue();
		if (vl.equals(nodeVal)) {
			return tmp;
		}
		// 查找子
		List<Node> list = tmp.getChildren();
		Node n = find(list, nodeVal);
		return n;
	}

	private Node find(List<Node> list, Object nodeVal) {
		List<Node> tmpList = new ArrayList<Node>();
		for (Node n : list) {
			Object val = n.getValue();
			if (val.equals(nodeVal)) {
				return n;
			}
			if (!n.isLeaf()) {
				tmpList.addAll(n.getChildren());
			}
		}
		// 当没有子node时候跳出
		if (null == tmpList || tmpList.size() == 0) {
			return null;
		}
		return find(tmpList, nodeVal);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object v) {
		this.value = v;
	}

}
