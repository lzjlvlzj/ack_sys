package org.ack.sys.file.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    private Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    @Value("${user.conf.file.path}")
    private  String staticPath = "";
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.debug("自定义静态路径:{}", staticPath);
        /*这里需要表明是文件系统，而不是当前文件夹下的子文件夹*/
        staticPath = "file:" + staticPath;
        registry.addResourceHandler("/**").addResourceLocations(staticPath,"classpath:/static/","classpath:/public/","classpath:/resources");
        super.addResourceHandlers(registry);
    }
}
