package com.miniBOM.service;

import com.miniBOM.dto.CreateAttributeDto;
import com.miniBOM.dto.DeleteAttributeDto;
import com.miniBOM.dto.GetAttributeDto;
import com.miniBOM.dto.UpdateAttributeDto;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;

public interface AttributeService {


    Result<Pair> get(GetAttributeDto attributeDto,short pageSize,short curPage);

    Result<Pair> update(UpdateAttributeDto attributeDto);

    Result<Pair> add(CreateAttributeDto attributeDto);

    Result<Pair> delete(DeleteAttributeDto attributeDto);

}
