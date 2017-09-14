package org.ack.common.tree;

import java.util.ArrayList;
import java.util.List;

import org.ack.pojo.Menu;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TreeTest {
    
	List<Menu> menuList = new ArrayList<Menu>();
	Tree tree = new Tree(0);
	@Before
	public void init(){
		Menu m0 = new Menu();
		m0.setId(0);
		m0.setMenuName("tianditu");
		tree = new Tree(m0);
    	/*Menu m1 = new Menu();
    	m1.setId(1);
    	m1.setParentId(0);
    	m1.setMenuName("用户管理");
    	
    	Menu m3 = new Menu();
    	m3.setId(3);
    	m3.setParentId(0);
    	m3.setMenuName("项目管理");
    	
    	Menu m2 = new Menu();
    	m2.setId(2);
    	m2.setParentId(1);
    	m2.setMenuName("用户添加");*/
    	
    	
    	Menu m4 = new Menu();
    	m4.setId(4);
    	m4.setParentId(0);
    	m4.setMenuName("项目任务管理");
    	
    	Menu m5 = new Menu();
    	m5.setId(5);
    	m5.setParentId(4);
    	m5.setMenuName("员工日志管理");
    	
    	Menu m6 = new Menu();
    	m6.setId(6);
    	m6.setParentId(5);
    	m6.setMenuName("员工日志删除");
    	
    	
    	/*menuList.add(m1);
    	menuList.add(m2);
    	menuList.add(m3);*/
    	menuList.add(m4);
    	menuList.add(m5);
    	menuList.add(m6);
    }
	
	@Test
	public void testList(){
		int len = menuList.size();
		System.out.println("menuList size : " + len);
		for(int i = 0; i < len; i++){
			Menu m = menuList.get(i);
			System.out.println("菜单id : " + m.getId() + " ,菜单名称 : " + m.getMenuName());
		}
	}
	
	@Test
	public void testFind(){
		/*Node<Menu> node = new Node<Menu>();
		node.setValue(6);
		Node<Menu> n = tree.find(node);
		System.out.println(n);*/
		
		
	}
	
	@Test
	public void testAdd(){
		final ObjectMapper mapper = new ObjectMapper();
		for(Menu m : menuList){
			System.out.println("循环-----------------");
			Node  parent = new Node();
			Menu mp = new Menu();
			mp.setId(m.getParentId());
			parent.setValue(mp);
			
			Node node = new Node();
			node.setValue(m);
			node.setParent(parent);
			tree.add(node);
		}
		String s = "";
		try {
			s = mapper.writeValueAsString(tree);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(s);
		/*System.out.println("--------------------");
		Node node = new Node();
		Menu m4 = new Menu();
    	m4.setId(4);
		node.setValue(m4);
		Node n = tree.find(node);
		System.out.println(n);
		System.out.println(n.getValue());*/

		
		
	}
	@Test
	public void testEquals(){
		Object o1 = 1;
		Object o2 = 1;
		
		System.out.println(o1 == o2);
		
		System.out.println(o1.equals(o2));
		
	}
}
