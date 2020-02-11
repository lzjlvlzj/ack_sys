package org.ack.sys.cms.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ack.sys.base.common.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.impl.DefaultKaptcha;


@Controller
public class CaptchaController {
	private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);
	@Autowired
	DefaultKaptcha defaultKaptcha;

	
	@RequestMapping("/captcha.jpg")
	public void captcha(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("验证码服务");
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = defaultKaptcha.createText();
		logger.debug("验证码:{}" , capText);
		// store the text in the session
		HttpSession session = request.getSession();
		logger.debug("sid = {}", session.getId());
		session.setAttribute(Content.SESSION_KEY_KAPTCHA, capText);
		// create the image with the text
		BufferedImage bi = defaultKaptcha.createImage(capText);
		ServletOutputStream out = null;
		// write the data out

		try {
			out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.flush();
		} catch (Exception e) {
			logger.error("生成验证码出错了.", e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
