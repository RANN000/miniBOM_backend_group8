package com.miniBOM.service;

import com.miniBOM.pojo.Attribute;
import com.miniBOM.pojo.Classification;

import java.util.List;

public interface ClassificationService {
    void add(Classification classification);

    List<Attribute> list(String searchKey);

    void update(Classification classification);

    void delete(Classification classification);
}
