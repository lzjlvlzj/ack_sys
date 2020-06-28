package org.ack.sys.pojo;

import java.util.List;

public class PortalCard {
    private String title;
    private String imgUrl;
    private String url;
    private List<PortalArticle> list;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PortalArticle> getList() {
        return list;
    }

    public void setList(List<PortalArticle> list) {
        this.list = list;
    }

}
