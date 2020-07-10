package org.ack.sys.file.service;

import org.ack.sys.base.common.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 上传文件操作，返回文件访问路径
     * 必须指定上传文件路径（比如：存储后的名称）
     * @param userPath 用户指定的相对路径
     * @param file 真实的文件
     * @return
     */
    public ResponseResult upload(String userPath, MultipartFile file);
}
