package com.miniBOM.service;

import com.miniBOM.pojo.Attribute;

import java.util.List;

public interface AttributeService {
    void add(Attribute attribute);

    void update(Attribute attribute);

    void delete(Attribute attribute);

    List<Attribute> list(String searchKey);
}
