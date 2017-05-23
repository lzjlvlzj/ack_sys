package org.ack.service.impl;

import java.util.Set;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.MenuMapper;
import org.ack.pojo.Menu;
import org.ack.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单业务处理
 * 
 * @author ack
 *
 */
@Service
public class MenuServiceImpl extends AckMapperServiceImpl<Menu, Integer>
		implements MenuService {
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	protected AckMapper<Menu, Integer> getAckMapper() {
		return menuMapper;
	}

	@Override
	public Set<Menu> findByIds(String[] ids) {
		return menuMapper.findByIds(ids);
	}

	@Override
	public Set<Menu> findMenuByPId(Integer pid) {
		return menuMapper.findMenuByPId(pid);
	}

}
