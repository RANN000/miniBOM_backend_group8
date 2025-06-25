package com.miniBOM.service;


import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreatePartDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateVO;
import com.miniBOM.pojo.Bom.BOMDelete.BOMDeleteDTO;
import com.miniBOM.pojo.Bom.BOMShow.BOMShowVO;
import com.miniBOM.pojo.Bom.BOMUpdate.BOMUpdateDTO;
import com.miniBOM.pojo.Bom.BOMUpdate.BOMUpdateVO;

import com.miniBOM.pojo.Result;

import java.util.List;


public interface BOMService {


    BOMCreateVO add(BOMCreateDTO bomCreateDTO);

    List<BOMShowVO> show(Long sourceId);

    BOMCreateVO addPart(BOMCreatePartDTO bomCreatePartDTO);

    Result showFather(Long partId);

    Result showRoot(Long partId);

    BOMUpdateVO update(BOMUpdateDTO bomUpdateDTO);

    void delete(BOMDeleteDTO bomDeleteDTO);
}
