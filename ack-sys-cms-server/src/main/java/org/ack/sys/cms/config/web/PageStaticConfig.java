package org.ack.sys.cms.config.web;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

//@Configuration
public class PageStaticConfig {
	//@Bean
	public TemplateEngine templateEngine() {
		ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
		classLoaderTemplateResolver.setPrefix("/templates/");
		classLoaderTemplateResolver.setSuffix(".html");
		classLoaderTemplateResolver.setCacheable(false);
		classLoaderTemplateResolver.setCharacterEncoding("utf-8");
		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(classLoaderTemplateResolver);
		return engine;
	}

}
