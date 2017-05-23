package org.ack.admin.web.template;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 封装返回ztree的数据
 * 
 * @author ack
 *
 */
public class ZTreeNode implements Serializable {
	
	private static final long serialVersionUID = -8390236462845324453L;
	
    private Integer id;                            // id
    private String name;                           // 节点名称
    private boolean isParent;                      // 非叶子节点
    
    @JsonProperty(value = "pId") 
    private Integer pId;                           // 上一级父节点id
    
    private String value;                          // 节点的值
    private List<ZTreeNode> subs;                  // 子节点
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public Integer getPId() {
		return pId;
	}
	public void setPId(Integer pId) {
		this.pId = pId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<ZTreeNode> getSubs() {
		return subs;
	}
	public void setSubs(List<ZTreeNode> subs) {
		this.subs = subs;
	}
}
