package org.ack.sys.portal.service.impl;

import org.ack.sys.base.persist.page.Page;
import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.pojo.PortalMenu;
import org.ack.sys.portal.pojo.Card;
import org.ack.sys.portal.service.CardService;
import org.ack.sys.portal.service.PortalArticleService;
import org.ack.sys.portal.service.PortalMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);
    @Autowired
    private PortalMenuService portalMenuServiceImpl;
    @Autowired
    private PortalArticleService portalArticleServiceImpl;
    @Value("${user.conf.menu}")
    private String menuIds;

    @Override
    public List<Card> findCard() {
        logger.debug("menuId = {}", menuIds);
        String[] str = menuIds.split(",");
        List<Card> list = new ArrayList<Card>();
        for(int i = 0; i < str.length; i++){
            long id = Long.parseLong(str[i]);
            Card card = getCard(id);
            list.add(card);
        }
        return list;
    }

    private Card getCard(long id) {
        Card card = new Card();
        Page<PortalArticle> page = new Page<PortalArticle>();
        page.setPageSize(3);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuId", id);
        page.setCondition(map);
        page = portalArticleServiceImpl.findPage(page);
        List<PortalArticle> list = page.getResult();
        if(null == list || list.size() == 0){
            logger.warn("没有查询到菜单id为[{}]的数据", id);
            return null;
        }
        PortalArticle pa = list.get(0);
        PortalMenu pm = pa.getPortalMenu();
        card.setPortalMenu(pm);
        card.setPortalArticleList(list);
        return card;
    }
}
