package org.ack.sys.cms.web.controller.portal;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Validation;
import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.cms.service.portal.PortalArticleService;
import org.ack.sys.cms.web.controller.BaseController;
import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.pojo.PortalMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/portal/article")
public class PortalArticleController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(PortalArticleController.class);
    @Autowired
    private PortalArticleService portalArticleServiceImpl;

    @AckPermission("portal:article:add OR portal:article:edit")
    @PostMapping("/imgUpload")
    @ResponseBody
    public ResponseResult imgUpload(MultipartHttpServletRequest request) {
		MultipartFile file = getSingleMultipartFile(request);
		if (null == file) {
			return new ResponseResult(400, "上传文件不能为空", null);
		}
        ResponseResult result = portalArticleServiceImpl.upload(file, 1);
        return result;
    }

    @AckPermission("portal:article:add OR portal:article:edit")
    @PostMapping("/fileUpload")
    @ResponseBody
    public ResponseResult fileUpload(MultipartHttpServletRequest request) {
		MultipartFile file = getSingleMultipartFile(request);
		if (null == file) {
			return new ResponseResult(400, "上传文件不能为空", null);
		}
        ResponseResult result = portalArticleServiceImpl.upload(file, 0);
        return result;
    }

    @AckPermission("portal:article:add OR portal:article:edit")
    @PostMapping("/mediaUpload")
    @ResponseBody
    public ResponseResult mediaUpload(MultipartHttpServletRequest request) {
        MultipartFile file = getSingleMultipartFile(request);
        if (null == file) {
            return new ResponseResult(400, "上传文件不能为空", null);
        }
        ResponseResult result = portalArticleServiceImpl.upload(file, 2);
        return result;
    }


    public ResponseResult publish(Long id) {
        int r = portalArticleServiceImpl.publish(id);
        ResponseResult responseResult = new ResponseResult(200, r);
        return responseResult;
    }

    @AckPermission("portal:article:view")
    @PostMapping("/findPage")
    @ResponseBody
    public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
        logger.debug("查询文章列表");
        pageRequest.setOrderColumn("createTime");
        Page<PortalArticle> page = portalArticleServiceImpl.findPage(pageRequest);
        ResponseResult result = new ResponseResult(200, page);
        return result;
    }

    @AckPermission("portal:article:add")
    @PostMapping("/add")
    @ResponseBody
    public ResponseResult insert(@RequestBody @Validated PortalArticle article,
                                 BindingResult result, HttpServletRequest request) {
        logger.debug("新建文章");
        ResponseResult responseResult = Validation.getValidationResult(result);
        if (null != responseResult) {
            return responseResult;
        } else {
            int r = portalArticleServiceImpl.insert(article);
            int code = 500;
            String msg = "";
            Object data = null;
            if (r == 1) {
                code = 200;
                msg = "";
                data = r;
            } else if (r == -1) {
                code = 400;
                msg = "文章已存在,请更换文章标题。";
                data = r;
            }
            return new ResponseResult(code, msg, data);
        }

    }

    @AckPermission("portal:article:edit")
    @PatchMapping("/edit")
    @ResponseBody
    public ResponseResult edit(@RequestBody PortalArticle menu, HttpServletRequest request, HttpServletResponse response) {
        int r = portalArticleServiceImpl.update(menu);
        return new ResponseResult(200, r);
    }

    @AckPermission("portal:article:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseResult delete(@RequestBody PortalArticle article, HttpServletRequest request,
                                 HttpServletResponse response) {
        int code = 200;
        String msg = "";
        int rt = portalArticleServiceImpl.delete(article);
        return new ResponseResult(code, msg, rt);
    }


}
