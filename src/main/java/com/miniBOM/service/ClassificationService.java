package com.miniBOM.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miniBOM.pojo.ClassificationDto.*;
import com.miniBOM.pojo.ClassificationVo.ListClassificationVo;
import com.miniBOM.pojo.ClassificationVo.OneClassificationVo;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;

import java.util.List;

public interface ClassificationService {

    Result<OneClassificationVo> add(CreateClassificationDto classificationDto);


    Result<OneClassificationVo> update(UpdateClassificationDto classificationDto);

    Result delete(Long deleteId);

    Result<List<OneClassificationVo>> list(ListClassificationDto dto) throws JsonProcessingException;

    Result<OneClassificationVo> getById(Long id);

//    Result getAttribute(Long id);

//    Result addAttribute(AddClassificationNodeAttributeDto classificationNodeAttributeDto);
//
//    Result deleteAttribute(DeleteClassificationNodeAttributeDto classificationNodeAttributeDto);
}
