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
import com.miniBOM.pojo.Bom.BOMSearch.BOMRootVo;

import com.miniBOM.pojo.Bom.BOMSearch.BOMShowFatherVO;

import com.miniBOM.pojo.Result;
import com.miniBOM.service.BOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BOMServiceImpl implements BOMService {

    @Autowired
    BOMDao BOMDao;


    @Override
    public BOMCreateVO add(BOMCreateDTO bomCreateDTO) {

        try {
            return BOMDao.add(bomCreateDTO);
        } catch (Exception e) {
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
    public Result showFather(Long partId) {
        BOMShowFatherVO bomShowFatherVO = BOMDao.showFather(partId);
        if(bomShowFatherVO == null) {
            return Result.error("不存在父项");
        }
        return Result.success(bomShowFatherVO);
    }

    @Override
    public Result showRoot(Long partId) {
        BOMShowFatherVO root = BOMDao.showRoot(partId);
        if(root == null) {
            BOMRootVo bomRootVo = new BOMRootVo();
            bomRootVo.setPartId(partId);
            bomRootVo.setPartName(BOMDao.findPartNameById(partId));
            return Result.success(bomRootVo);
        }
        return Result.success(BOMDao.toRoot(root));
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
