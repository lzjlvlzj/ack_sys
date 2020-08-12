package org.ack.sys.cms.service.portal;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.base.util.FileUtil;
import org.ack.sys.cms.config.store.FileStoreConfig;
import org.ack.sys.cms.service.DictionaryService;
import org.ack.sys.cms.service.UploadService;
import org.ack.sys.persist.mapper.portal.PortalArticleMapper;
import org.ack.sys.pojo.Dictionary;
import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.pojo.PortalArticleDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PortalArticleServiceImpl extends PageServiceImpl<PortalArticle, Long> implements PortalArticleService {

    private static final Logger logger = LoggerFactory.getLogger(PortalArticleServiceImpl.class);
    @Autowired
    private PortalArticleDetailService portalArticleDetailServiceImpl;
    @Autowired
    private PortalArticleMapper portalArticleMapper;
    @Autowired
    private DictionaryService dictionaryServiceImpl;
    @Autowired
    private UploadService uploadServiceImpl;

    @Override
    protected PageDao<PortalArticle, Long> getPageDao() {
        return portalArticleMapper;
    }

    @Override
    @Transactional
    public int insert(PortalArticle article) {
        // 校验是否已经存在
        int count = portalArticleMapper.findByTitle(article.getTitle());
        if (count > 0) {
            logger.debug("文章[{}]已经存在，重复添加！", article.getTitle());
            return -1;
        }
        //插入文章内容
        PortalArticleDetail pad = article.getPortalArticleDetail();
        int r = portalArticleDetailServiceImpl.insert(pad);
        if (r == 1) {
            article.setPageView(0);
            article.setDeleteStatus(0);
            article.setDetailId(pad.getId());
            /*插入文章meta信息*/
            r = portalArticleMapper.insert(article);
        }
        return r;
    }


    @Override
    public int update(PortalArticle article) {
        return 0;
    }

    @Override
    public int delete(PortalArticle article) {
        return 0;
    }

    @Override
    public int publish(Long id) {
        logger.info("重新生成首页");
        logger.info("重新生成列表页");
        logger.info("生成文章列表");
        return 0;
    }

    @Override
    public ResponseResult upload(MultipartFile file, int flag) {
        String originalFilename = file.getOriginalFilename();
        logger.debug("原始文件名称:{}", originalFilename);
        String[] suffixs = null;
        long fileSize = file.getSize();
        long thresholdSize = 0L;
        String pathKey = null;
        /*文件*/
        if (flag == 0) {
            suffixs = Content.DEFAULT_FILE_SUFFIX;
            // 100M
            thresholdSize = 20 * 1024 * 1024 * 1024L;
            pathKey = FileStoreConfig.PORTAL_ARTICLE_FILE_PATH_KEY;
        }
        /*图片*/
        if (flag == 1) {
            suffixs = Content.DEFAULT_IMG_SUFFIX;
            thresholdSize = 10 * 1024 * 1024 * 1024L;
            pathKey = FileStoreConfig.PORTAL_ARTICLE_IMG_PATH_KEY;
        }
        /*影音*/
        if (flag == 2) {
            suffixs = Content.DEFAULT_MEDIA_SUFFIX;
            thresholdSize = 100 * 1024 * 1024 * 1024L;
            pathKey = FileStoreConfig.PORTAL_ARTICLE_MEDIA_PATH_KEY;
        }
        if (fileSize <= 0 || fileSize > thresholdSize) {
            return new ResponseResult(400, "文件大小超出范围", null);
        }
        boolean b = FileUtil.checkSuffix(originalFilename, suffixs);
        if (!b) {
            return new ResponseResult(400, "格式不正确", null);
        }
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        Dictionary dictionary = dictionaryServiceImpl.findByKey(pathKey);
        String path = dictionary.getValue();
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        /*以年月为文件夹*/
        String timeFolder = getTimeFolder();
        path = path + timeFolder + "/" + FileUtil.createFileNameByUUID() + suffix;
        logger.debug("部门大图的相对路径: {}", path);
        return uploadServiceImpl.upload(path, file);
    }

    private String getTimeFolder() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String folderName = sdf.format(date);
        return folderName;
    }


}
