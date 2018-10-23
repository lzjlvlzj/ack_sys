package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.Brand;

public interface BrandMapper extends AckMapper<Brand, Integer>{
    Brand findByProductId(Integer id);
}