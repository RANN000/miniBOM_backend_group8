package com.miniBOM.service.impl;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsModifierDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionCreateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionUpdateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import com.miniBOM.dao.AttributeDao;
import com.miniBOM.pojo.AttributeDto.ListAttributeDto;
import com.miniBOM.pojo.AttributeVo.ListAttributeVo;
import com.miniBOM.pojo.AttributeVo.OneAttributeVo;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeDao attributeDao;

    @Override
    public Result<OneAttributeVo> add(EXADefinitionCreateDTO attributeDto) {

        // 调用dao层创建属性
        EXADefinitionViewDTO createdAttribute = attributeDao.add(attributeDto);

        // 转换为前端视图对象
        OneAttributeVo resultView = new OneAttributeVo();
        BeanUtils.copyProperties(createdAttribute, resultView);

        return Result.success(resultView);
    }

    @Override
    public Result<OneAttributeVo> update(EXADefinitionUpdateDTO attributeDto) {

        // 调用dao层更新属性
        EXADefinitionViewDTO updateAttribute = attributeDao.update(attributeDto);

        // 转换为前端视图对象
        OneAttributeVo resultView = new OneAttributeVo();
        BeanUtils.copyProperties(updateAttribute, resultView);
        return Result.success(resultView);
    }

    @Override
    public Result deleteAttributes(PersistObjectIdsModifierDTO attributeDto) {
        attributeDao.deleteAttributes(attributeDto);
        return Result.success();
    }

    @Override
    public Result<ListAttributeVo> list(ListAttributeDto attributeDto) {
        ListAttributeVo listAttributeVo = attributeDao.list(attributeDto);
        return Result.success(listAttributeVo);
    }

    @Override
    public long count(String searchKey) {
        return attributeDao.count(searchKey);
    }

    @Override
    public Result<OneAttributeVo> getById(Long id) {
        List<EXADefinitionViewDTO> list = attributeDao.getById(id);

        // 增加空值校验
        if (list == null || list.isEmpty()) {
            return Result.error("未找到属性");
        }

        OneAttributeVo attributeVO = new OneAttributeVo();
        BeanUtils.copyProperties(list.get(0), attributeVO);
        return Result.success(attributeVO);
    }

    @Override
    public Result delete(Long deleteId) {
        attributeDao.delete(deleteId);
        return Result.success("删除成功");
    }
}
