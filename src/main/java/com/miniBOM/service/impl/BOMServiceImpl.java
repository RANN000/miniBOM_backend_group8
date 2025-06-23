package com.miniBOM.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miniBOM.dao.BOMDao;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateVO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowDTO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowVO;
import com.miniBOM.service.BOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BOMServiceImpl implements BOMService {

    @Autowired
    BOMDao BOMDao;


    @Override
    public BOMCreateVO add(BOMCreateDTO bomCreateDTO) {

        try {
            return BOMDao.add(bomCreateDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BOMShowVO> show(String code) {
        try {
            return BOMDao.show(code);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
