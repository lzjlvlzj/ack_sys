package org.ack.sys.cms.components;


import java.io.FileWriter;
import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class StaticHtmlService {
	
	protected Logger logger = LoggerFactory.getLogger(StaticHtmlService.class);
	
	@Autowired
	private TemplateEngine templateEngine;//这是thymeleaf模板处理引擎
	
	//@Autowired
	///private ApplicationContext appContext;//这是Spring容器上下文
	
	@Value(value = "${portal.page-positon}")
	private String baseLocation;
	
	/**
	 * 生成html静态页面
	 * @param modelAndView
	 * @param request
	 * @param response
	 */
	public boolean process(String freeTempName,Context context,String outFilePath) {
		FileWriter fileWriter = null;
		try {
			String path = baseLocation + outFilePath;
			fileWriter = new FileWriter(path);
			templateEngine.process(freeTempName, context,fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 用于更新时删除之前生成的静态页面
	 * @param fileName
	 */
	public void deleteHtmlPage(String fileName) {
		
	}
 
}
