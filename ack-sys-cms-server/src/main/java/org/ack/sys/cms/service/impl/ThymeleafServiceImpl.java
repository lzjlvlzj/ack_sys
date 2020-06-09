package org.ack.sys.cms.service.impl;

import org.ack.sys.cms.service.ThymeleafService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class ThymeleafServiceImpl implements ThymeleafService {

    @Autowired
    private TemplateEngine templateEngine;
    private static final Logger logger = LoggerFactory.getLogger(ThymeleafServiceImpl.class);
    public static final String destPath = "F:\\idea_workspace\\me\\ack_sys\\ack-sys-portal-page";

    public Map<String, Object> loadModel(Long id) {
        // 讲道理，这里加载的数据是根据id从数据库查询出来的，我这里就写固定了
        Map<String, Object> map = new HashMap<>();
        map.put("name", "tellsea");
        map.put("age", 20);
        map.put("email", "3210054449@qq.com");
        return map;
    }


    @Override
    public void createHtml(Long spuId) {
       // 上下文
        Context context = new Context();
        context.setVariables(loadModel(spuId));
        // 输出流
        File dest = new File(destPath, spuId + ".html");
        if (dest.exists()) {
            dest.delete();
        }
        try (PrintWriter writer = new PrintWriter(dest, "UTF-8")) {
            // 生成html
            templateEngine.process("id", context, writer);
        } catch (Exception e) {
            logger.error("[静态页服务]：生成静态页异常", e);
        }
    }

    @Override
    public void deleteHtml(Long id) {

    }
}
