package org.ack.sys.cms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.util.HttpClientUtil;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.cms.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UploadServiceImpl implements UploadService {
    private Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
    @Value("${portal.img-server}")
    private  String imgServer = "";
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public ResponseResult upload(String path, MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", path);
        File file = null;
        List<File> list = new ArrayList<File>();
        try {
            file = multipartFileToFile(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add(file);
        String rt = HttpClientUtil.sendHttpPost(imgServer, map, list);
        ResponseResult result = null;
        try {
            result = mapper.readValue(rt, ResponseResult.class);
        } catch (JsonProcessingException e) {
            logger.debug("解析json出错:", rt);
            result = new ResponseResult(500, "系统出错", null);
        }
        return result;
    }
    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
