package com.miniBOM.service.impl;

import com.miniBOM.dao.ClassificationDao;
import com.miniBOM.pojo.Attribute;
import com.miniBOM.pojo.Classification;
import com.miniBOM.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public void add(Classification classification) {
        classificationDao.add(classification);
    }

    @Override
    public List<Attribute> list(String searchKey) {
        return classificationDao.list(searchKey);
    }

    @Override
    public void update(Classification classification) {
        classificationDao.update(classification);
    }

    @Override
    public void delete(Classification classification) {
        classificationDao.delete(classification);
    }
}
