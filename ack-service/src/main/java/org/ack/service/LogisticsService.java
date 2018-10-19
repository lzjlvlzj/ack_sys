package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Logistics;

import java.util.List;

public interface LogisticsService extends AckMapperService<Logistics, Long>{

    List<Logistics> findByClientId(Integer id);
}
