package org.ack.sys.pojo;

import org.ack.sys.base.pojo.BasePojo;

import javax.validation.constraints.NotBlank;

public class PortalArticle extends BasePojo {

	@NotBlank(message = "{portal.article.title.notblank}")
	private String title;
	@NotBlank(message = "{portal.article.author.notblank}")
	private String author;
	@NotBlank(message = "{portal.article.summery.notblank}")
	private String summery;

	private Integer pageView;

	private String source;

	private String url;

	private String remark;

	private Integer status;

	private Long detailId;

	private Long menuId;

	private PortalMenu portalMenu;

	private PortalArticleDetail portalArticleDetail;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public String getSummery() {
		return summery;
	}

	public void setSummery(String summery) {
		this.summery = summery;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public PortalArticleDetail getPortalArticleDetail() {
		return portalArticleDetail;
	}

	public void setPortalArticleDetail(PortalArticleDetail portalArticleDetail) {
		this.portalArticleDetail = portalArticleDetail;
	}
	public PortalMenu getPortalMenu() {
		return portalMenu;
	}

	public void setPortalMenu(PortalMenu portalMenu) {
		this.portalMenu = portalMenu;
	}
	@Override
	public String toString() {
		return "PortalArticle [author=" + author + ", pageView=" + pageView + ", source=" + source + ", remark="
				+ remark + ", detailId=" + detailId + ", menuId=" + menuId + ", portalArticleDetail="
				+ portalArticleDetail + "]";
	}
}