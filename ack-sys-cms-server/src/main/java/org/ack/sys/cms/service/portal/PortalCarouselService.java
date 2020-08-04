package org.ack.sys.cms.service.portal;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.service.PageService;
import org.ack.sys.pojo.PortalCarousel;
import org.springframework.web.multipart.MultipartFile;

public interface PortalCarouselService extends PageService<PortalCarousel, Long> {
    public ResponseResult upload(MultipartFile file);
}
