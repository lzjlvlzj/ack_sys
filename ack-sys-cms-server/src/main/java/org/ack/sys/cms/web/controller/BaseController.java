package org.ack.sys.cms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.pojo.User;
import org.ack.sys.cms.web.util.WebUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;
import java.util.Set;

public class BaseController {
	public User getCurrentUser(HttpServletRequest request) {
		return WebUtil.getCurrentUser(request);
	}

	public MultipartFile getSingleMultipartFile(MultipartHttpServletRequest  request){
		Map<String, MultipartFile> map = request.getFileMap();
		int size = map.size();
		if(size == 0){
			return null;
		}
		Set<String> set = map.keySet();
		MultipartFile file  = null;
		for(String key : set){
			file = map.get(key);
			break;
		}
		return file;
	}
}
