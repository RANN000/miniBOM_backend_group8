package com.miniBOM.service;

import com.miniBOM.pojo.Attribute;

import java.util.List;

public interface ClassificationService {
    void add(Attribute attribute);

    List<Attribute> list(String searchKey);

    void update(Attribute attribute);

    void delete(Attribute attribute);
}
