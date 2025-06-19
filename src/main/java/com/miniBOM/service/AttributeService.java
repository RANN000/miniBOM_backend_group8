package com.miniBOM.service;

import com.miniBOM.dto.CreateAttributeDto;
import com.miniBOM.dto.DeleteAttributeDto;
import com.miniBOM.dto.GetAttributeDto;
import com.miniBOM.dto.UpdateAttributeDto;
import com.miniBOM.pojo.Result;

public interface AttributeService {


    Result list(GetAttributeDto attributeDto);

    Result update(UpdateAttributeDto attributeDto);

    Result add(CreateAttributeDto attributeDto);

    Result delete(DeleteAttributeDto attributeDto);

}
