package org.ack.sys.cms.service.impl;


import java.util.List;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.cms.persist.mapper.RoleMapper;
import org.ack.sys.cms.pojo.Role;
import org.ack.sys.cms.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**用戶邏輯
 * @author ack
 *
 */
@Service
public class RoleServiceImpl extends PageServiceImpl<Role, Long> implements RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleMapper roleMapper;

	@Override
	protected PageDao<Role, Long> getPageDao() {
		logger.debug("mapper is {}", roleMapper);
		return roleMapper;
	}
	
	@Override
	@Transactional
	public int insert(Role t) {
       Role role = findRoleByName(t.getName());
       if(null != role) {
    	   logger.debug("角色:{}已存在", t.getName());
    	   return -1;
       }
	   return super.insert(t);
	}

	@Override
	@Transactional
	public int update(Role t) {
	   Role role = findRoleByName(t.getName());
       if(null != role) {
    	   if (role.getId() != t.getId()) {
    		   logger.debug("角色:{}已存在", t.getName());
        	   return -1;
    	   }
       }
	   return super.update(t);
	}

	@Override
	@Transactional
	public int batchDelete(List<Role> list) {
		int size = list.size();
		int r = 0;
		for(int i = 0 ; i < size; i++) {
			Role role = list.get(i);
			role.setDeleteStatus(1);
			int rt = update(role);
			r = r + rt;
		}
		logger.debug("需要修改的数据为{}条,实际修改{}条", size, r);
		return r;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=true)
	public Role findRoleByName(String name) {
		return roleMapper.findRoleByName(name);
	}




	

}
