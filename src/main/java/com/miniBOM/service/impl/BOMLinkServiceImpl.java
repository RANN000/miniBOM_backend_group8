package com.miniBOM.service.impl;

import com.miniBOM.dao.BOMLinkDao;
import com.miniBOM.pojo.BOMLink;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.BOMLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BOMLinkServiceImpl implements BOMLinkService {

    @Autowired
    private BOMLinkDao bomLinkDao;

    @Override
    public Result create(BOMLink bomLink) {
        return bomLinkDao.create(bomLink);
    }
}
