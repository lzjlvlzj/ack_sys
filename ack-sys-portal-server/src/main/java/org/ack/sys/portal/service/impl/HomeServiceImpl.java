package org.ack.sys.portal.service.impl;

import org.ack.sys.pojo.PortalMenu;
import org.ack.sys.portal.pojo.Card;
import org.ack.sys.portal.service.CardService;
import org.ack.sys.portal.service.HomeService;
import org.ack.sys.portal.service.PortalArticleService;
import org.ack.sys.portal.service.PortalMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private PortalMenuService portalMenuServiceImpl;
    @Autowired
    private PortalArticleService portalArticleServiceImpl;
    @Autowired
    private CardService cardServiceImpl;



    @Override
    public PortalMenu findMenu() {
        return portalMenuServiceImpl.findSortedMenu();
    }

    @Override
    public List<Card> findCardList() {
        return cardServiceImpl.findCard();
    }

}
