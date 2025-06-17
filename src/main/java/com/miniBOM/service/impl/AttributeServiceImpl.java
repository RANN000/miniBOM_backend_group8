package com.miniBOM.service.impl;
import com.miniBOM.mapper.AttributeMapper;
import com.miniBOM.pojo.Attribute;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public void add(Attribute attribute) {
        attributeMapper.add(attribute);
    }

    @Override
    public void update(Attribute attribute) {
        attributeMapper.update(attribute);
    }

    @Override
    public void delete(Attribute attribute) {
        attributeMapper.delete(attribute);
    }

    @Override
    public List<Attribute> list(String searchKey) {
        return attributeMapper.list(searchKey);
    }

}
