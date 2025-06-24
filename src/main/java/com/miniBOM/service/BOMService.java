package com.miniBOM.service;


import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateVO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowDTO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowFatherVO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowVO;
import com.miniBOM.pojo.Result;

import java.util.List;


public interface BOMService {


    BOMCreateVO add(BOMCreateDTO bomCreateDTO);

    List<BOMShowVO> show(Long sourceId);

    Result showFather(Long partId);

    Result showRoot(Long partId);
}
