package com.miniBOM.service;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsModifierDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionCreateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionUpdateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import com.miniBOM.pojo.AttributeDto.ListAttributeDto;
import com.miniBOM.pojo.AttributeVo.ListAttributeVo;
import com.miniBOM.pojo.AttributeVo.OneAttributeVo;
import com.miniBOM.pojo.Result;

import java.util.List;


public interface AttributeService {


    Result<List<OneAttributeVo>> list(ListAttributeDto listAttributeDto);

    Result<OneAttributeVo> update(EXADefinitionUpdateDTO attributeDto);

    Result<OneAttributeVo> add(EXADefinitionCreateDTO attributeDto);

    Result deleteAttributes(PersistObjectIdsModifierDTO attributeDto);

    long count(String searchKey);

    Result<OneAttributeVo> getById(Long id);

    Result delete(Long deleteId);
}
