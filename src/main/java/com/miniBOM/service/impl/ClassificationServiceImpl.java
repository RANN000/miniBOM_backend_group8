package com.miniBOM.service.impl;

import com.miniBOM.dao.ClassificationDao;
import com.miniBOM.dto.*;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public Result<Pair> add(CreateClassificationDto classificationDto) {
        return classificationDao.add(classificationDto);
    }

    @Override
    public Result<Pair> get(GetClassicificationDto classificationDto) {
        return classificationDao.get(classificationDto);
    }

    @Override
    public Result<Pair> update(UpdateClassificationDto classificationDto) {
        return classificationDao.update(classificationDto);
    }

    @Override
    public Result<Pair> delete(DeleteClassificationDto classificationDto) {
        return classificationDao.delete(classificationDto);
    }

    @Override
    public Result<Pair> list(short pageSize,short curPage) {
        return classificationDao.list(pageSize,curPage);
    }

    @Override
    public Result addAttribute(AddClassificationNodeAttributeDto classificationNodeAttributeDto) {
        return classificationDao.addAttribute(classificationNodeAttributeDto);
    }

    @Override
    public Result deleteAttribute(DeleteClassificationNodeAttributeDto classificationNodeAttributeDto) {
        return classificationDao.deleteAttribute(classificationNodeAttributeDto);
    }
}
