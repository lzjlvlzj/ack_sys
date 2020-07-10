package org.ack.sys.portal.service.impl;

import org.ack.sys.pojo.PortalCarousel;
import org.ack.sys.pojo.PortalMenu;
import org.ack.sys.portal.pojo.Card;
import org.ack.sys.portal.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    private static final Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    private PortalMenuService portalMenuServiceImpl;
    @Autowired
    private PortalArticleService portalArticleServiceImpl;
    @Autowired
    private CardService cardServiceImpl;
    @Autowired
    private PortalCarouselService portalCarouselServiceImpl;


    @Override
    public PortalMenu findMenu() {
        return portalMenuServiceImpl.findSortedMenu();
    }

    @Override
    public List<Card> findCardList() {
        return cardServiceImpl.findCard();
    }

    @Override
    public List<PortalCarousel> findCarouselByPosition() {
        logger.debug("查询轮播");
        int position = 0;
        return portalCarouselServiceImpl.findByPosition(0);
    }

}
