package org.ack.auth.authenticate.tags;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义权限标签
 * 
 * @author ack
 * */
public class AckPermissionSupport extends TagSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3551352760991727969L;


	@Override  
    public int doStartTag() throws JspException {  
        //内置一个pageContext对象，我们之前说到pageContext对象，它里面是封装了9个隐式对象  
        HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();  
        JspWriter out = this.pageContext.getOut();  
        String ip = request.getRemoteAddr();  
        try {  
            out.print(ip);  
        } catch (IOException e) {  
            throw new RuntimeException(e);  
        }  
        return super.doStartTag();  
    }  

}
