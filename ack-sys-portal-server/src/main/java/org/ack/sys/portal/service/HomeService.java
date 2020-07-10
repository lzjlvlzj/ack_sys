package org.ack.sys.portal.service;

import org.ack.sys.pojo.PortalCarousel;
import org.ack.sys.pojo.PortalMenu;
import org.ack.sys.portal.pojo.Card;

import java.util.List;

public interface HomeService {
    /**
     * 查询菜单
     * @return
     */
    public PortalMenu findMenu();

    /**
     * 查询card
     * @return
     */
    List<Card> findCardList();

    List<PortalCarousel> findCarouselByPosition();
}
