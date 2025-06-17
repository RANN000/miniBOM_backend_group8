package com.miniBOM.service.impl;

import com.miniBOM.mapper.ClassificationMapper;
import com.miniBOM.pojo.Attribute;
import com.miniBOM.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationMapper classificationMapper;

    @Override
    public void add(Attribute attribute) {
        classificationMapper.add(attribute);
    }

    @Override
    public List<Attribute> list(String searchKey) {
        return classificationMapper.list(searchKey);
    }

    @Override
    public void update(Attribute attribute) {
        classificationMapper.update(attribute);
    }

    @Override
    public void delete(Attribute attribute) {
        classificationMapper.delete(attribute);
    }
}
