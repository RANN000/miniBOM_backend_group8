package com.miniBOM.service;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsModifierDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionCreateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionUpdateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AttributeService {


    List<EXADefinitionViewDTO> list(String searchKey, Integer pageSize, Integer curPage);

    EXADefinitionViewDTO update(EXADefinitionUpdateDTO attributeDto);

    EXADefinitionViewDTO add(EXADefinitionCreateDTO attributeDto);

    void delete(PersistObjectIdsModifierDTO attributeDto);

    long count(String searchKey);

    List<EXADefinitionViewDTO> getById(Long id);
}
