package org.ack.sys.cms.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.base.util.FileUtil;
import org.ack.sys.base.util.MD5Util;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.cms.persist.mapper.UserMapper;
import org.ack.sys.cms.pojo.Dictionary;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.PersonalService;
import org.ack.sys.cms.web.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonalServiceImpl extends PageServiceImpl<User, Long> implements PersonalService {
	private static final Logger logger = LoggerFactory.getLogger(PersonalServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DictionaryServiceImpl dictionaryServiceImpl;
    private static final String AVATAR_URL_KEY = "avatar_uri";
    private static final String AVATAR_TYPE_KEY = "avatar_type";
	@Override
	protected PageDao<User, Long> getPageDao() {
		return userMapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findUserByUserName(String username) {
		logger.debug("username = {}", username);
		User user = userMapper.findUserByUserName(username);
		user.setPassword("");
		return user;
	}

	@Override
	public int changePwd(User user) {
		User u = userMapper.findUserByUserName(user.getUsername());
		if(!MD5Util.md5(user.getPassword()).equals(u.getPassword())) {
			logger.debug("原密码不正确");
			return 2;
		}
		String newPass = user.getNewPassword();
		String password = MD5Util.md5(newPass);
		user.setPassword(password);
		
		return userMapper.updateByUserName(user);
	}

	@Override
	public String uploadAvatar(MultipartFile file, HttpServletRequest request) {
		String originalFilename = file.getOriginalFilename();
		logger.debug("头像原名称 = {}", originalFilename);
		Dictionary dictType = dictionaryServiceImpl.findByKey(AVATAR_TYPE_KEY);
        boolean typeStatus = FileUtil.typeMatch(originalFilename, dictType.getValue());
        if(!typeStatus) {
        	logger.debug("图像图片格式不正确");
        	return "";
        }
		String classPath = FileUtil.getClassesPath();
		Dictionary dict = dictionaryServiceImpl.findByKey(AVATAR_URL_KEY);
		User user = WebUtil.getCurrentUser(request);
		String oldAvatar = classPath + Content.STATIC_RESOURCE_PREFIX + user.getAvatar();
		logger.debug("原头像地址:{}", oldAvatar);
		StringBuffer sb = new StringBuffer();
		sb.append(dict.getValue());
		sb.append(user.getUsername());
		sb.append("/");
		sb.append(StringUtils.uuidString());
		sb.append(".");
		//获得文件后缀
		String[] strs = originalFilename.split("\\.");
		String sufix = strs[strs.length -1];
		sb.append(sufix);
		String localAddress = classPath + Content.STATIC_RESOURCE_PREFIX +  sb.toString();
		logger.debug("头像磁盘地址 :{}", localAddress);
		File dest = FileUtil.createFile(localAddress, "/");
		//写文件
		try {
			file.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			logger.error("头像上传失败", e);
			return "";
		}
		//上传成功后删除原头像
		try {
			FileUtil.forceDelete(new File(oldAvatar));
		} catch (IOException e) {
			logger.debug("删除原头像失败",e);
			return "";
		}
		// 更新数据库
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setAvatar(sb.toString());
		int r = userMapper.update(newUser);
		if(r!= 1) {
			logger.debug("更新用户：{} 的头像失败",user.getUsername());
		}
		//更新session作用用户的头像
		user.setAvatar(sb.toString());
		return sb.toString();
	}

}
