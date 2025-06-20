package com.miniBOM.service.impl;
import com.miniBOM.dao.PartDao;
import com.miniBOM.pojo.*;
import com.miniBOM.pojo.Part.Branch;
import com.miniBOM.pojo.Part.Master;
import com.miniBOM.pojo.Part.Part;
import com.miniBOM.pojo.Part.PartCreate.PartCreateReqDTO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateDTO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryResDTO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryVO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateDTO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateReqDTO;
import com.miniBOM.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDao partDao;

    @Override
    public PartCreateVO add(PartCreateDTO partDTO) {

        PartCreateReqDTO partCreateDTO = new PartCreateReqDTO();
        if(partDTO.getCode() != null){
            partCreateDTO.setId(partDTO.getCode());
        }
        if(partDTO.getName()!=null && !partDTO.getName().isEmpty()){
            partCreateDTO.setName(partDTO.getName());
        }
        if (partDTO.getDefaultUnit() != null){
            partCreateDTO.setDefaultUnit(partDTO.getDefaultUnit());
        }
        Master master = new Master();
        master.setId(partDTO.getCode());
        partCreateDTO.setMaster(master);
        Branch branch = new Branch();
        branch.setId(partDTO.getCode());
        partCreateDTO.setBranch(branch);

//        Pair[] extAttrs = new Pair[partDTO.getAttributes().size()];
        List<Pair> extAttrs = new ArrayList<>();
        for(Map.Entry<String,String> entry:partDTO.getAttributes().entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            extAttrs.add(new Pair(key,value));
        }
        //TODO加入分类可能有问题
        extAttrs.add(new Pair("Classcification",partDTO.getCategory()));
        partCreateDTO.setExtAttrs(extAttrs);
        return partDao.add(partCreateDTO);
    }

    @Override
    public Part find(Part part) {
        return partDao.find(part);
    }

    @Override
    public List<PartHistoryVO> listAllVersion(String masterId) {
        List<PartHistoryResDTO> historyResDTOS = partDao.listAllVersion(masterId);
        List<PartHistoryVO> partHistoryVOS = new ArrayList<>();
        for (PartHistoryResDTO partHistoryResDTO : historyResDTOS) {
            PartHistoryVO partHistoryVO = new PartHistoryVO();
            partHistoryVO.setVersion(partHistoryResDTO.getVersion()+"."+partHistoryResDTO.getIteration());
            partHistoryVO.setUpdatedAt(partHistoryResDTO.getLastUpdateTime());
            partHistoryVOS.add(partHistoryVO);
        }
        return partHistoryVOS;
    }

    @Override
    public List<Part> list(String searchKey){return partDao.list(searchKey);}

    @Override
    public void update(PartUpdateDTO partUpdateDTO) {
        String masterId=partUpdateDTO.getCode();
        partDao.checkOut(masterId);

        PartUpdateReqDTO  partUpdateReqDTO = new PartUpdateReqDTO();
        //TODO 为空如何抛异常
        if(partUpdateDTO.getCode()!=null &&!partUpdateDTO.getCode().isEmpty()){
            partUpdateReqDTO.setId(partUpdateDTO.getCode());
        }
        if(partUpdateDTO.getName()!=null && !partUpdateDTO.getName().isEmpty()){
            partUpdateReqDTO.setName(partUpdateDTO.getName());
        }
        List<Pair> extAttrs = new ArrayList<>();
        if(partUpdateDTO.getAttributes()!=null && !partUpdateDTO.getAttributes().isEmpty()){
            for(Map.Entry<String,String> entry:partUpdateDTO.getAttributes().entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                extAttrs.add(new Pair(key,value));
            }
            partUpdateReqDTO.setExtAttrs(extAttrs);
        }
        partDao.update(partUpdateReqDTO);

        partDao.checkIn(masterId);
    }

    @Override
    public void checkOut(String masterId) {
        partDao.checkOut(masterId);
    }

    @Override
    public void checkIn(String masterId) {
        partDao.checkIn(masterId);
    }

    @Override
    public void delete(String id) {
        partDao.delete(id);
    }
}
