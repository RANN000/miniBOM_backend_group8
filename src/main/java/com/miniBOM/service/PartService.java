package com.miniBOM.service;


import com.miniBOM.pojo.Part.Part;
import com.miniBOM.pojo.Part.PartCreate.PartCreateVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateDTO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryVO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateDTO;

import java.util.List;


public interface PartService {
    PartCreateVO add(PartCreateDTO partCreateDTO);

    Part find(Part part);

    List<PartHistoryVO> listAllVersion(String masterId);

    void update(PartUpdateDTO partUpdateDTO);

    void checkOut(String masterId);

    void checkIn(String masterId);

    void delete(String id);

    List<Part> list(String searchKey);




}
