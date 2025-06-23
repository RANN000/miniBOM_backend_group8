package com.miniBOM.service.impl;

import com.miniBOM.dao.BOMUsesOccurrenceDao;
import com.miniBOM.pojo.BOMUsesOccurrence;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.BOMUsesOccurrenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BOMUsesOccurrenceImpl implements BOMUsesOccurrenceService {

    @Autowired
    private BOMUsesOccurrenceDao bomUsesOccurrenceDao;

    @Override
    public Result create(BOMUsesOccurrence bomUsesOccurrence) {
        return bomUsesOccurrenceDao.create(bomUsesOccurrence);
    }
}
