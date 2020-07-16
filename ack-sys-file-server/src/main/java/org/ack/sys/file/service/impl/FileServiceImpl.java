package org.ack.sys.file.service.impl;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.util.FileUtil;
import org.ack.sys.file.pojo.FileStatus;
import org.ack.sys.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Value("${user.conf.file.path}")
    private String basePath = null;
    @Value("${user.conf.url.path}")
    private String baseUrl = null;
    @Value("${user.conf.suffix.path}")
    private String suffix = null;
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public ResponseResult upload(String userPath, MultipartFile file) {
        //文件绝对路径
        if (!basePath.endsWith(File.separator)) {
            basePath = basePath + File.separator;
        }
        String path = basePath + userPath;
        String originalName = file.getOriginalFilename();
        logger.debug("原文件名称:{}", originalName);
        logger.debug("校验文件是否符合要求");
        ResponseResult result = checkFile(originalName);
        if (result.getCode() != 200) {
            return result;
        }
        File newFile = FileUtil.createFile(path);
        logger.debug("存储文件的绝对路径为:{}", newFile.getAbsolutePath());
        try {
            file.transferTo(newFile);
            String url = baseUrl + userPath;
            logger.debug("上传文件的访问路径:{}", url);
            return new ResponseResult(FileStatus.UPLOAD_SUCCESS.getCode(), url);
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("上传文件出错", e);
        }
        return new ResponseResult(FileStatus.UPLOAD_ERROR.getCode(), FileStatus.UPLOAD_ERROR.getMsg(), null);
    }

    private ResponseResult checkFile(String originalName) {
        ResponseResult result = new ResponseResult(200, "");
        return result;
    }

    private boolean checkSuffix(String originalName) {
        return true;
    }
}
