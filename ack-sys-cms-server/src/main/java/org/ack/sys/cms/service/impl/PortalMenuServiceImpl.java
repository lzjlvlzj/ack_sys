package org.ack.sys.cms.service.impl;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.cms.service.PortalMenuService;
import org.ack.sys.persist.mapper.PortalMenuMapper;
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
        return portalMenuMapper;
    }
}
