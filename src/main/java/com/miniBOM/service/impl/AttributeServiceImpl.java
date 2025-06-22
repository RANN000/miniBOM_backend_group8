package com.miniBOM.service.impl;
import com.miniBOM.dao.AttributeDao;
import com.miniBOM.pojo.AandCDto.CreateAttributeDto;
import com.miniBOM.pojo.AandCDto.DeleteAttributeDto;
import com.miniBOM.pojo.AandCDto.ListAttributeDto;
import com.miniBOM.pojo.AandCDto.UpdateAttributeDto;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeDao attributeDao;

    @Override
    public Result<Pair> add(CreateAttributeDto attributeDto) {
        return attributeDao.add(attributeDto);
    }

    @Override
    public Result<Pair> update(UpdateAttributeDto attributeDto) {
        return attributeDao.update(attributeDto);
    }

    @Override
    public Result<Pair> delete(DeleteAttributeDto attributeDto) {
        return attributeDao.delete(attributeDto);
    }

    @Override
    public Result<Pair> list(ListAttributeDto attributeDto, short pageSize, short curPage) {
        return attributeDao.list(attributeDto,pageSize,curPage);
    }


}
