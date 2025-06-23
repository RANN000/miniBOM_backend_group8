package com.miniBOM.service.impl;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsModifierDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionCreateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionUpdateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import com.miniBOM.dao.AttributeDao;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeDao attributeDao;

    @Override
    public EXADefinitionViewDTO add(EXADefinitionCreateDTO attributeDto) {
        return attributeDao.add(attributeDto);
    }

    @Override
    public EXADefinitionViewDTO update(EXADefinitionUpdateDTO attributeDto) {
        return attributeDao.update(attributeDto);
    }

    @Override
    public void delete(PersistObjectIdsModifierDTO attributeDto) {
        attributeDao.delete(attributeDto);
    }

    @Override
    public List<EXADefinitionViewDTO> list(String searchKey, Integer pageSize, Integer curPage) {
        return attributeDao.list(searchKey,pageSize,curPage);
    }

    @Override
    public long count(String searchKey) {
        return attributeDao.count(searchKey);
    }

    @Override
    public List<EXADefinitionViewDTO> getById(Long id) {
        return attributeDao.getById(id);
    }
}
