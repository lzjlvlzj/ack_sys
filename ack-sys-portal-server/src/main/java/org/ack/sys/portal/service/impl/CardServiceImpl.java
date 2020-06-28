package org.ack.sys.portal.service.impl;

import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.portal.pojo.Card;
import org.ack.sys.portal.service.CardService;
import org.ack.sys.portal.service.PortalArticleService;
import org.ack.sys.portal.service.PortalMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);
    @Autowired
    private PortalMenuService portalMenuServiceImpl;
    @Autowired
    private PortalArticleService portalArticleServiceImpl;
    @Value("${user.menuId}")
    private String menuIds;

    @Override
    public List<Card> findCard() {
        logger.debug("menuId = {}", menuIds);
        /***/
        return null;
    }
}
