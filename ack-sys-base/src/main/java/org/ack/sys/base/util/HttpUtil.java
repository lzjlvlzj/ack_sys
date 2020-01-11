package org.ack.sys.base.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpUtil {
	
	public static ServletRequestAttributes getServletRequestAttributes() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes;
	}
	
	/**获得HttpServletResponse
	 * @return
	 */
	public static HttpServletResponse getHttpServletResponse() {
		ServletRequestAttributes attributes = getServletRequestAttributes();
		if(null == attributes) {
			return null;
		}
		HttpServletResponse response = attributes.getResponse();
		return response;
	}
	
	public static Object getSessionStore(HttpServletRequest request, String key) {
		HttpSession session = getHttpSession(request);
		return session.getAttribute(key);
	}
	
	public static Object getSessionStore(String key) {
		HttpSession session = getHttpSession(null);
		return session.getAttribute(key);
	}
	
	/**
	 * 返回HttpSession
	 * */
	public static HttpSession getHttpSession(HttpServletRequest request) {
		if(null == request) {
			request = getHttpServletRequest();
		}
		if(null == request) {
			return null;
		}
		return request.getSession();
	}
	
	/**
	 * 返回HttpServletRequest
	 * */
	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes attributes = getServletRequestAttributes();
		if(null == attributes) {
			return null;
		}
        HttpServletRequest request = attributes.getRequest();
        return request;
	}

	/**返回json
	 * @param response
	 * @param json
	 */
	public static void responseJson(HttpServletResponse response, String json) {
		responseJson(response, 200, json);

	}
	/**返回json
	 * @param response
	 * @param json
	 */
	public static void responseJson(HttpServletResponse response, int code, String json) {
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setStatus(code);
		try {
			writer = response.getWriter();
			writer.print(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				writer.close();
			}

		}

	}
}
