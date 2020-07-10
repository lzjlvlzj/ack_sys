package org.ack.sys.portal.service.impl;

import org.ack.sys.persist.mapper.portal.PortalCarouselMapper;
import org.ack.sys.pojo.PortalCarousel;
import org.ack.sys.portal.service.PortalCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortalCarouselServiceImpl implements PortalCarouselService {
    @Autowired
    private PortalCarouselMapper portalCarouselMapper;
    @Override
    public List<PortalCarousel> findByPosition(Integer position) {
        return portalCarouselMapper.findByPosition(position);
    }
}
