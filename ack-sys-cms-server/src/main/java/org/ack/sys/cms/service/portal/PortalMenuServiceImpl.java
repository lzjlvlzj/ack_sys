package org.ack.sys.cms.service.portal;

import java.util.ArrayList;
import java.util.List;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.base.util.FileUtil;
import org.ack.sys.cms.service.DictionaryService;
import org.ack.sys.cms.service.UploadService;
import org.ack.sys.persist.mapper.portal.PortalMenuMapper;
import org.ack.sys.pojo.Dictionary;
import org.ack.sys.pojo.PortalMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PortalMenuServiceImpl extends PageServiceImpl<PortalMenu, Long> implements PortalMenuService {
    private static final Logger logger = LoggerFactory.getLogger(PortalMenuServiceImpl.class);
    private static final String PORTAL_MENU_IMG_PATH_KEY = "portal_menu_img";
    @Autowired(required = false)
    private PortalMenuMapper portalMenuMapper;
	@Autowired
	private DictionaryService dictionaryServiceImpl;
	@Autowired
	private UploadService uploadServiceImpl;

    @Override
    protected PageDao<PortalMenu, Long> getPageDao() {
    	logger.debug("mapper is： {}", portalMenuMapper);
        return portalMenuMapper;
    }

	@Override
	public ResponseResult upload(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		logger.debug("原始文件名称:{}", originalFilename);
		boolean b = FileUtil.checkSuffix(originalFilename, Content.DEFAULT_IMG_SUFFIX);
		if (!b) {
			return new ResponseResult(400, "格式不正确", null);
		}
		int index = originalFilename.lastIndexOf(".");
		String suffix = originalFilename.substring(index);
		Dictionary dictionary = dictionaryServiceImpl.findByKey(PORTAL_MENU_IMG_PATH_KEY);
		String path = dictionary.getValue();
		if (!path.endsWith("/")) {
			path = path + "/";
		}
		path = path + FileUtil.createFileNameByUUID() + suffix;
		logger.debug("部门大图的相对路径: {}", path);
		return uploadServiceImpl.upload(path, file);
	}

	@Override
	public PortalMenu findSortedMenu() {
		List<PortalMenu> list = portalMenuMapper.findAll();
		PortalMenu menu = new PortalMenu();
		menu.setId(0L);
		list = getChildren(list, menu).getChildren();
		menu.setChildren(list);
		return menu;
	}

	private PortalMenu getChildren(List<PortalMenu> list, PortalMenu root) {
		List<PortalMenu> children = new ArrayList<PortalMenu>();
		int size = list.size();
		for(int i = 0; i < size; i++) {
			PortalMenu menu = list.get(i);
			if(root.getId() == menu.getParentId()) {
				children.add(menu);
				getChildren(list, menu);
			}
		}
		root.setChildren(children);
		return root;
	}
}
