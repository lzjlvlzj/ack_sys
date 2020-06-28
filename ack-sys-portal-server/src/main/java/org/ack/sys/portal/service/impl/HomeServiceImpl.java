package org.ack.sys.portal.service.impl;

import org.ack.sys.pojo.PortalMenu;
import org.ack.sys.portal.service.HomeService;
import org.ack.sys.portal.service.PortalMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private PortalMenuService portalMenuServiceImpl;



    @Override
    public PortalMenu findMenu() {
        return portalMenuServiceImpl.findSortedMenu();
    }

}
