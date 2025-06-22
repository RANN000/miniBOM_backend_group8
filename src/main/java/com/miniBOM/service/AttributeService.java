package com.miniBOM.service;

import com.miniBOM.pojo.AandCDto.CreateAttributeDto;
import com.miniBOM.pojo.AandCDto.DeleteAttributeDto;
import com.miniBOM.pojo.AandCDto.ListAttributeDto;
import com.miniBOM.pojo.AandCDto.UpdateAttributeDto;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;

public interface AttributeService {


    Result<Pair> list(ListAttributeDto attributeDto, short pageSize, short curPage);

    Result<Pair> update(UpdateAttributeDto attributeDto);

    Result<Pair> add(CreateAttributeDto attributeDto);

    Result<Pair> delete(DeleteAttributeDto attributeDto);

}
