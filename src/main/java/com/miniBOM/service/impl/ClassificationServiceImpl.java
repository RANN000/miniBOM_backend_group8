package com.miniBOM.service.impl;

import com.miniBOM.mapper.ClassificationMapper;
import com.miniBOM.pojo.Attribute;
import com.miniBOM.pojo.Classification;
import com.miniBOM.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationMapper classificationMapper;

    @Override
    public void add(Classification classification) {
        classificationMapper.add(classification);
    }

    @Override
    public List<Attribute> list(String searchKey) {
        return classificationMapper.list(searchKey);
    }

    @Override
    public void update(Classification classification) {
        classificationMapper.update(classification);
    }

    @Override
    public void delete(Classification classification) {
        classificationMapper.delete(classification);
    }
}
