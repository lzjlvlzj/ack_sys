package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.Logistics;

import java.util.List;

public interface LogisticsMapper extends AckMapper<Logistics, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(Logistics record);

    int insertSelective(Logistics record);

    Logistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Logistics record);

    int updateByPrimaryKey(Logistics record);

    List<Logistics> findByClientId(Integer clientId);
}