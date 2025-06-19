package com.miniBOM.service;

import com.miniBOM.dto.CreateClassificationDto;
import com.miniBOM.dto.DeleteClassificationDto;
import com.miniBOM.dto.GetClassicificationDto;
import com.miniBOM.dto.UpdateClassificationDto;
import com.miniBOM.pojo.Result;

public interface ClassificationService {

    Result add(CreateClassificationDto classificationDto);

    Result query(GetClassicificationDto classificationDto);

    Result update(UpdateClassificationDto classificationDto);

    Result delete(DeleteClassificationDto classificationDto);
}
