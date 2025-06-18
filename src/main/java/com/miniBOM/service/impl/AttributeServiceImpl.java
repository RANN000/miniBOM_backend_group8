package com.miniBOM.service.impl;
import com.miniBOM.dao.AttributeDao;
import com.miniBOM.pojo.Attribute;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeDao attributeDao;

    @Override
    public void add(Attribute attribute) {
        attributeDao.add(attribute);
    }

    @Override
    public void update(Attribute attribute) {
        attributeDao.update(attribute);
    }

    @Override
    public void delete(Attribute attribute) {
        attributeDao.delete(attribute);
    }

    @Override
    public List<Attribute> list(String searchKey) {
        return attributeDao.list(searchKey);
    }

}
