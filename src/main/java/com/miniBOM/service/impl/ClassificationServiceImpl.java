package com.miniBOM.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import com.miniBOM.dao.ClassificationDao;
import com.miniBOM.pojo.AttributeVo.OneAttributeVo;
import com.miniBOM.pojo.ClassificationDto.*;
import com.miniBOM.pojo.ClassificationVo.ListClassificationVo;
import com.miniBOM.pojo.ClassificationVo.OneClassificationVo;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.ClassificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public Result<OneClassificationVo> add(CreateClassificationDto classificationDto) {
        OneClassificationVo oneClassificationVo = classificationDao.add(classificationDto);
        return Result.success(oneClassificationVo);
    }

    @Override
    public Result<OneClassificationVo> update(UpdateClassificationDto classificationDto) {
        OneClassificationVo oneClassificationVo = classificationDao.update(classificationDto);
        return Result.success(oneClassificationVo);
    }

    @Override
    public Result delete(Long deleteId) {
        classificationDao.delete(deleteId);
        return Result.success();
    }

    @Override
    public Result<ListClassificationVo> list(ListClassificationDto classificationDto) throws JsonProcessingException {
        ListClassificationVo listClassificationVo = classificationDao.list(classificationDto);
        return Result.success(listClassificationVo);
    }

    @Override
    public Result<OneClassificationVo> getById(Long id) {
        List<ClassificationNodeViewDTO> list = classificationDao.getById(id);
        OneClassificationVo oneClassificationVo = new OneClassificationVo();

        // 增加空值校验
        if (list == null || list.isEmpty()) {
            return Result.error("未找到对应ID的属性");
        }

        BeanUtils.copyProperties(list.get(0), oneClassificationVo);
        return Result.success(oneClassificationVo);
    }

//    @Override
//    public Result addAttribute(AddClassificationNodeAttributeDto classificationNodeAttributeDto) {
//        return classificationDao.addAttribute(classificationNodeAttributeDto);
//    }
//
//    @Override
//    public Result deleteAttribute(DeleteClassificationNodeAttributeDto classificationNodeAttributeDto) {
//        return classificationDao.deleteAttribute(classificationNodeAttributeDto);
//    }
}
