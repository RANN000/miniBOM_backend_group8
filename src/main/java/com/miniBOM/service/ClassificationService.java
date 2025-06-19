package com.miniBOM.service;

import com.miniBOM.dto.CreateClassificationDto;
import com.miniBOM.dto.DeleteClassificationDto;
import com.miniBOM.dto.GetClassicificationDto;
import com.miniBOM.dto.UpdateClassificationDto;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;

public interface ClassificationService {

    Result<Pair> add(CreateClassificationDto classificationDto);

    Result<Pair> get(GetClassicificationDto classificationDto);

    Result<Pair> update(UpdateClassificationDto classificationDto);

    Result<Pair> delete(DeleteClassificationDto classificationDto);
}
