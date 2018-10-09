package org.ack.common.tree;

import java.util.LinkedList;
import java.util.List;

public class Node {
	
    protected Object value;       
    protected Node parent;
    protected List<Node> children = new LinkedList<Node>();
    
    public Node(){
    	
    }
    public Node(Object value, Node parent){
    	this.value = value;
    	this.parent = parent;
    }
    
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
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
	@Override
	public String toString() {
		
		return value.toString();
	}
}
