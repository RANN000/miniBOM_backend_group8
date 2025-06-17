package com.miniBOM.mapper;

import com.miniBOM.pojo.Attribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassificationMapper {
    void add(Attribute attribute);

    List list(String searchKey);

    void update(Attribute attribute);

    void delete(Attribute attribute);
}
