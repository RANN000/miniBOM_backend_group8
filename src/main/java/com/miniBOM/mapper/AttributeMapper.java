package com.miniBOM.mapper;

import com.miniBOM.pojo.Attribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttributeMapper {
    void add(Attribute attribute);

    void update(Attribute attribute);

    void delete(Attribute attribute);

    List list(String searchKey);
}
