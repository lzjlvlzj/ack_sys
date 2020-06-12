package org.ack.sys.pojo;

import org.ack.sys.base.pojo.BasePojo;

public class PortalArticleMeta extends BasePojo {

	private String author;

	private Integer pageView;

	private String source;

	private String remark;

	private Integer detailId;

	private Integer menuId;

	private PortalArticleDetail portalArticleDetail;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public PortalArticleDetail getPortalArticleDetail() {
		return portalArticleDetail;
	}

	public void setPortalArticleDetail(PortalArticleDetail portalArticleDetail) {
		this.portalArticleDetail = portalArticleDetail;
	}

	@Override
	public String toString() {
		return "PortalArticleMeta [author=" + author + ", pageView=" + pageView + ", source=" + source + ", remark="
				+ remark + ", detailId=" + detailId + ", menuId=" + menuId + ", portalArticleDetail="
				+ portalArticleDetail + "]";
	}
}