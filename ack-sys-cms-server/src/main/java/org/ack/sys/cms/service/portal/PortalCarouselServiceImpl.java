package org.ack.sys.cms.service.portal;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.base.util.FileUtil;
import org.ack.sys.cms.config.store.FileStoreConfig;
import org.ack.sys.cms.service.DictionaryService;
import org.ack.sys.cms.service.UploadService;
import org.ack.sys.persist.mapper.portal.PortalCarouselMapper;
import org.ack.sys.pojo.Dictionary;
import org.ack.sys.pojo.PortalCarousel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PortalCarouselServiceImpl extends PageServiceImpl<PortalCarousel, Long> implements PortalCarouselService {
    private Logger logger = LoggerFactory.getLogger(PortalCarouselServiceImpl.class);
    @Autowired(required = false)
    private PortalCarouselMapper portalCarouselMapper;
    @Autowired
    private DictionaryService dictionaryServiceImpl;
    @Autowired
    private UploadService uploadServiceImpl;

    @Override
    protected PageDao<PortalCarousel, Long> getPageDao() {
        logger.debug("轮播mapper");
        return portalCarouselMapper;
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
        Dictionary dictionary = dictionaryServiceImpl.findByKey(FileStoreConfig.PORTAL_INDEX_IMG_PATH_KEY);
        String path = dictionary.getValue();
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        path = path + FileUtil.createFileNameByUUID() + suffix;
        logger.debug("部门大图的相对路径: {}", path);
        return uploadServiceImpl.upload(path, file);
    }
}
