package org.ack.sys.cms.web.controller.portal;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.cms.service.portal.PortalCarouselService;
import org.ack.sys.pojo.PortalCarousel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/portal/carousel")
public class PortalCarouselController {
    private static final Logger logger = LoggerFactory.getLogger(PortalCarouselController.class);
    @Autowired
    private PortalCarouselService portalCarouselServiceImpl;

    @AckPermission("portal:index:view OR portal:carousel:view")
    @PostMapping("/findPage")
    @ResponseBody
    public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
        logger.debug("查询轮播");
        pageRequest.setOrderColumn("createTime");
        Page<PortalCarousel> page = portalCarouselServiceImpl.findPage(pageRequest);
        ResponseResult result = new ResponseResult(200, page);
        return result;
    }
    @AckPermission("portal:carousel:edit")
    @PatchMapping("/edit")
    @ResponseBody
    public ResponseResult update(@RequestBody PortalCarousel portalCarousel, HttpServletRequest request, HttpServletResponse response){
        int r = portalCarouselServiceImpl.update(portalCarousel);
        int code = 500;
        String msg = "系统错误";
        Object data = null;
        if (r == 1) {
            code = 200;
            msg = "";
            data = r;
        }
        return new ResponseResult(code, msg, data);
    }

    @AckPermission("portal:carousel:edit")
    @PostMapping("/upload")
    @ResponseBody
    public ResponseResult upload(MultipartHttpServletRequest request){
        Map<String, MultipartFile> map = request.getFileMap();
        int size = map.size();
        if(size == 0){
            return new ResponseResult(400, "上传文件不能为空", null);
        }
        Set<String> set = map.keySet();
        MultipartFile file  = null;
        for(String key : set){
            file = map.get(key);
            break;
        }
        ResponseResult result = portalCarouselServiceImpl.upload(file);
        return result;
    }

}
