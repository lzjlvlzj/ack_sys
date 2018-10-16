package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.Flow;

public interface FlowMapper extends AckMapper<Flow, Integer> {

    int insert(Flow flow);

    int insertSelective(Flow flow);
}