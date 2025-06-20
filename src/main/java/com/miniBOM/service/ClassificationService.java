package com.miniBOM.service;

import com.miniBOM.dto.*;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;

public interface ClassificationService {

    Result<Pair> add(CreateClassificationDto classificationDto);

    Result<Pair> get(GetClassicificationDto classificationDto);

    Result<Pair> update(UpdateClassificationDto classificationDto);

    Result<Pair> delete(DeleteClassificationDto classificationDto);

    Result<Pair> list(short pageSize,short curPage);

    Result addAttribute(AddClassificationNodeAttributeDto classificationNodeAttributeDto);

    Result deleteAttribute(DeleteClassificationNodeAttributeDto classificationNodeAttributeDto);
}
