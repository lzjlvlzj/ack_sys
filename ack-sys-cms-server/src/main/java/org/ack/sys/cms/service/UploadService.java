package org.ack.sys.cms.service;

import org.ack.sys.base.common.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult upload(String path, MultipartFile file);
}
