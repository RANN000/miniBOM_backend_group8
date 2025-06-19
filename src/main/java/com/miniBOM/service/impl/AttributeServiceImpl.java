package com.miniBOM.service.impl;
import com.miniBOM.dao.AttributeDao;
import com.miniBOM.dto.CreateAttributeDto;
import com.miniBOM.dto.DeleteAttributeDto;
import com.miniBOM.dto.GetAttributeDto;
import com.miniBOM.dto.UpdateAttributeDto;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeDao attributeDao;

    @Override
    public Result add(CreateAttributeDto attributeDto) {
        return attributeDao.add(attributeDto);
    }

    @Override
    public Result update(UpdateAttributeDto attributeDto) {
        return attributeDao.update(attributeDto);
    }

    @Override
    public Result delete(DeleteAttributeDto attributeDto) {
        return attributeDao.delete(attributeDto);
    }

    @Override
    public Result list(GetAttributeDto attributeDto) {
        return attributeDao.query(attributeDto);
    }

}
