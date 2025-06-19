package com.miniBOM.service.impl;

import com.miniBOM.dao.ClassificationDao;
import com.miniBOM.dto.CreateClassificationDto;
import com.miniBOM.dto.DeleteClassificationDto;
import com.miniBOM.dto.GetClassicificationDto;
import com.miniBOM.dto.UpdateClassificationDto;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public Result add(CreateClassificationDto classificationDto) {
        return classificationDao.add(classificationDto);
    }

    @Override
    public Result query(GetClassicificationDto classificationDto) {
        return classificationDao.list(classificationDto);
    }

    @Override
    public Result update(UpdateClassificationDto classificationDto) {
        return classificationDao.update(classificationDto);
    }

    @Override
    public Result delete(DeleteClassificationDto classificationDto) {
        return classificationDao.delete(classificationDto);
    }
}
