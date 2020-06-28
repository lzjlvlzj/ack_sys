package org.ack.sys.portal.service;

import org.ack.sys.base.service.BaseService;
import org.ack.sys.pojo.PortalMenu;

public interface PortalMenuService extends BaseService<PortalMenu, Long> {
    /**
     * 查询排好序的菜单
     *
     * @return PortalMenu
     */
    public PortalMenu findSortedMenu();
}
