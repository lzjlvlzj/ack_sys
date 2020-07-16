package org.ack.sys.cms.web.controller;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.cms.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

@Controller
public class UploadController {

    @Autowired
    private UploadService uploadServiceImpl;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseResult upload(MultipartHttpServletRequest request){
        Map<String, MultipartFile> map = request.getFileMap();
        int size = map.size();
        if(size == 0){
            return new ResponseResult(400, "上传文件不能为空", null);
        }

        return new ResponseResult(200, "success");
    }

}
