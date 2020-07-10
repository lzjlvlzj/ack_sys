package org.ack.sys.portal.service;

import org.ack.sys.pojo.PortalCarousel;

import java.util.List;

public interface PortalCarouselService {
    public List<PortalCarousel> findByPosition(Integer position);
}
