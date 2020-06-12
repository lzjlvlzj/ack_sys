package org.ack.sys.cms.service.portal;

import java.util.ArrayList;
import java.util.List;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.persist.mapper.portal.PortalMenuMapper;
import org.ack.sys.pojo.PortalMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalMenuServiceImpl extends PageServiceImpl<PortalMenu, Long> implements PortalMenuService {
    private static final Logger logger = LoggerFactory.getLogger(PortalMenuServiceImpl.class);
    @Autowired
    private PortalMenuMapper portalMenuMapper;

    @Override
    protected PageDao<PortalMenu, Long> getPageDao() {
    	logger.debug("mapper is： {}", portalMenuMapper);
        return portalMenuMapper;
    }

	@Override
	public PortalMenu findSortedMenu() {
		List<PortalMenu> list = portalMenuMapper.findAll();
		PortalMenu menu = new PortalMenu();
		menu.setId(0L);
		list = getChildren(list, menu).getChildren();
		menu.setChildren(list);
		return menu;
	}

	private PortalMenu getChildren(List<PortalMenu> list, PortalMenu root) {
		List<PortalMenu> children = new ArrayList<PortalMenu>();
		int size = list.size();
		for(int i = 0; i < size; i++) {
			PortalMenu menu = list.get(i);
			if(root.getId() == menu.getParentId()) {
				children.add(menu);
				getChildren(list, menu);
			}
		}
		root.setChildren(children);
		return root;
	}
}
