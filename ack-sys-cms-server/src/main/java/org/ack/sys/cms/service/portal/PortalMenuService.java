package org.ack.sys.cms.service.portal;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.service.PageService;
import org.ack.sys.pojo.PortalMenu;
import org.springframework.web.multipart.MultipartFile;

public interface PortalMenuService extends PageService<PortalMenu, Long> {

	/**
	 * 查询排好序的菜单
	 * 
	 * @return PortalMenu
	 */
	public PortalMenu findSortedMenu();

    ResponseResult upload(MultipartFile file);
}
