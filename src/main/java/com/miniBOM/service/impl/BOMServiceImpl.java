package com.miniBOM.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miniBOM.dao.BOMDao;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreatePartDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateVO;
import com.miniBOM.pojo.Bom.BOMDelete.BOMDeleteDTO;
import com.miniBOM.pojo.Bom.BOMShow.BOMShowVO;
import com.miniBOM.pojo.Bom.BOMUpdate.BOMUpdateDTO;
import com.miniBOM.pojo.Bom.BOMUpdate.BOMUpdateVO;
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
    public List<BOMShowVO> show(Long sourceId) {
        try {
            return BOMDao.show(sourceId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BOMCreateVO addPart(BOMCreatePartDTO bomCreatePartDTO) {
        try {
            return BOMDao.addPart(bomCreatePartDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BOMUpdateVO update(BOMUpdateDTO bomUpdateDTO) {
        return BOMDao.update(bomUpdateDTO);
    }

    @Override
    public void delete(BOMDeleteDTO bomDeleteDTO) {
        try {
            BOMDao.delete(bomDeleteDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
