package org.ack.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.common.tree.Node;
import org.ack.common.tree.Tree;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.UserMapper;
import org.ack.pojo.Menu;
import org.ack.pojo.Permission;
import org.ack.pojo.Role;
import org.ack.pojo.User;
import org.ack.service.PermissionService;
import org.ack.service.RoleService;
import org.ack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑实现
 * 
 * @author ack
 *
 */
@Service
public class UserServiceImpl extends AckMapperServiceImpl<User, Long> implements
		UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	/**
	 * 存放
	 */
	//private static Map<String, List<Role>> permissionMap= new HashMap<String, List<Role>>();
	/**
	 * 存放权限
	 */
	private static Map<String,List<Menu>> menuMap = new HashMap<String,List<Menu>>();

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleService roleServiceImpl;

	@Autowired
	private PermissionService permissionServiceImpl;

	@Override
	protected AckMapper<User, Long> getAckMapper() {
		return userMapper;
	}

	@Override
	public User findUserByLoginName(String loginName) {
		return userMapper.findUserByLoginName(loginName);
	}

	@Override
	public List<Permission> findAuthByUser(User user) {
		/*String loginName = user.getLoginName();
		if (null != permissionMap.get(loginName)) {
			return permissionMap.get(loginName);
		}
		List<Permission> permissionList = new ArrayList<Permission>();
		// 查询用户权限
		System.out.println(user);
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			// 根据角色查询权限
			Set<Permission> permissons = roleServiceImpl
					.findPermissoinsByRole(role);
			permissionList.addAll(permissons);
		}
		permissionMap.put(loginName, permissionList);*/
		return null;
	}
	
	private List<Menu> processMenus(Set<Menu> menuSet) {
		Menu root = new Menu();
		root.setId(0);//数据库中root id 为0
		Tree tree = new Tree(root);
	    List<Menu> list = new ArrayList<Menu>();
	    for(Menu menu : menuSet){
	    	Node  parent = new Node();
			Menu mp = new Menu();
			mp.setId(menu.getParentId());
			parent.setValue(mp);
			
			Node node = new Node();
			node.setValue(menu);
			node.setParent(parent);
			tree.add(node);
	    }
		return list;
	}
	
	private Tree getMenuTree(List<Menu> menuList) {
		Menu root = new Menu();
		root.setId(0);//数据库中root id 为0
		Tree tree = new Tree(root);
	    for(Menu menu : menuList){
	    	Node  parent = new Node();
			Menu mp = new Menu();
			mp.setId(menu.getParentId());
			parent.setValue(mp);
			
			Node node = new Node();
			node.setValue(menu);
			node.setParent(parent);
			tree.add(node);
	    }
		return tree;
	}


	@Override
	public List<Menu> findMenuByUser(User user) {
		String loginName = user.getLoginName();
		
		Set<Menu> menus = getMenus(user);
		// 组织菜单成树形结构
		List<Menu> menuList = processMenus(menus);
		menuMap.put(loginName, menuList);
		return menuList;
	}
	
	@Override
	public Tree findMenuTreeByUser(User user) {
        //String loginName = user.getLoginName();
		
		Set<Menu> menus = getMenus(user);
		List<Menu> menuList = new ArrayList<Menu>(menus.size());
		menuList.addAll(menus);
		//排序
		Collections.sort(menuList);
		Tree t = getMenuTree(menuList);
		//menuMap.put(loginName, t);
		return t;
	}

	private Set<Menu> getMenus(User user) {
		Set<Menu> menus = new HashSet<Menu>();
		Set<Role> roles = findRolesByUser(user);
		
		for(Role role : roles){
			Set<Menu> ms = roleServiceImpl.findMenusByRole(role);
		    for(Menu m : ms){
		    	menus.add(m);
		    }
		}
		return menus;
	}

	@Override
	public List<Role> findAllRoles() {
		return  roleServiceImpl.findAll();
	}

	@Override
	public Set<Role> findRolesByUser(User user) {
		user = findById(user.getId());
		String ids = user.getRoleIds();
		String[] roleIds = ids.split(",");
		Set<Role> roles = roleServiceImpl.findByIds(roleIds);
		return roles;
	}

	@Override
	public int updateRoleByUser(User user, Integer[] rid) {
		return 0;
	}

	@Override
	public Set<String> getPermissionString(User user) {
		Set<Menu> menus = getMenus(user);
		Set<String> set = new HashSet<String>();
		for(Menu m : menus){
			String s = m.getPermission();
			set.add(s);
		}
		return set;
	}

	@Override
	public List<User> findManagers(User currentUser) {
		Role role = roleServiceImpl.findManager();
		List<User> list = userMapper.findDepartmentUser(currentUser);
		List<User> managers = new ArrayList<User>();
		String roleId = role.getId().toString();
		for(User user : list){
			String ids = user.getRoleIds();
			if(ids.indexOf(roleId) >= 0){
				if(logger.isDebugEnabled()){
					String name = user.getSurname() + user.getName();
					logger.debug("项目经理为: {}", name);
				}
				managers.add(user);
			}
		}
		return managers;
	}

	@Override
	public List<Role> findRoleList(User user) {
		Set<Role> roles = user.getRoles();
		int max = findMaxWeight(roles);
		List<Role> list = roleServiceImpl.findAll();
		List<Role> roleList = new ArrayList<Role>();
		for(int i = 0; i < list.size(); i++){
			Role role = list.get(i);
			int weight = role.getWeight();
			if(weight < max){
				roleList.add(role);
			}
		}
		return roleList;
	}

	private int findMaxWeight(Set<Role> roles) {
		int max = 0;
		for(Role role : roles){
			int weight = role.getWeight();
			if(weight > max){
				max = weight;
			}
		}
		return max;
	}
}
