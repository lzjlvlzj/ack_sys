package org.ack.sys.file.web.controller;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.file.pojo.FileStatus;
import org.ack.sys.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.Map;

@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileServiceImpl;


    @PostMapping("/upload")
    @ResponseBody
    public ResponseResult upload(@RequestParam("path") String  path,  MultipartHttpServletRequest request) {
        String username = request.getParameter("username");
        logger.debug("username = {}", username);
        String ph = request.getParameter("path");
        logger.debug("user path = {}", username);
        Map<String, MultipartFile> fileMap = request.getFileMap();
        int size = fileMap.size();
        logger.debug("总共上传{}个文件", size);
        if(0 == size){
            return new ResponseResult(400, "上传文件不能为空");
        }
        Iterator<String> it = fileMap.keySet().iterator();
        String key = it.next();
        MultipartFile file = fileMap.get(key);
        if (file.isEmpty()) {
            logger.debug(FileStatus.FILE_IS_EMPTY.getMsg());
            return new ResponseResult(FileStatus.FILE_IS_EMPTY.getCode(), FileStatus.FILE_IS_EMPTY.getMsg());
        }
        ResponseResult result = new ResponseResult();
        if(StringUtils.isNotBlank(path)){
            result = fileServiceImpl.upload(path, file);
        } else {
            result.setCode(FileStatus.UPLOAD_ERROR.getCode());
            result.setMsg(FileStatus.UPLOAD_ERROR.getMsg());
            logger.debug(FileStatus.UPLOAD_ERROR.getMsg());
        }
        return result;
    }

    @GetMapping("/test")
    public String findImg() {
        logger.debug("测试");
        return "upload";
    }
}
