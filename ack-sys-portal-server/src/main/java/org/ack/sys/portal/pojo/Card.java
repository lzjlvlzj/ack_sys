package org.ack.sys.portal.pojo;

import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.pojo.PortalMenu;

import java.util.List;

public class Card {
    private PortalMenu portalMenu;
    private List<PortalArticle> portalArticleList;

    public PortalMenu getPortalMenu() {
        return portalMenu;
    }

    public void setPortalMenu(PortalMenu portalMenu) {
        this.portalMenu = portalMenu;
    }

    public List<PortalArticle> getPortalArticleList() {
        return portalArticleList;
    }

    public void setPortalArticleList(List<PortalArticle> portalArticleList) {
        this.portalArticleList = portalArticleList;
    }
}
