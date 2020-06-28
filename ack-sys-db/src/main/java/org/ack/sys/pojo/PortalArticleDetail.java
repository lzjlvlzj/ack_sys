package org.ack.sys.pojo;


public class PortalArticleDetail {
    private Long id;

    private String content;

    public Long getId() {
    	return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
	@Override
	public String toString() {
		return "PortalArticleDetail [id=" + id + ", content=" + content + "]";
	}
    
    
}