package org.ack.sys.cms.web.controller.portal;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.cms.service.portal.PortalMenuService;
import org.ack.sys.pojo.PortalMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 导航菜单
 * 
 * @author ack
 *
 */
@Controller
@RequestMapping("/portal/menu")
public class PortalMenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(PortalMenuController.class);
	
	@Autowired
	private PortalMenuService portalMenuServiceImpl;
	

	public ResponseResult findPortalMenu(@RequestBody PageRequest pageRequest) {
		logger.debug("查询门户菜单");
		PortalMenu portalMenu = portalMenuServiceImpl.findSortedMenu();
		return new ResponseResult(200, portalMenu);
	}
	
	
	
	

}
