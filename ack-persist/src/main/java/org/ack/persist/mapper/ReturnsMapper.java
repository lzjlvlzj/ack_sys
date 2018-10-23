package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.Returns;

public interface ReturnsMapper extends AckMapper<Returns, Long> {
    int insert(Returns record);

    int insertSelective(Returns record);

}